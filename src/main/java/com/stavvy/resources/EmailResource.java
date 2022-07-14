package com.stavvy.resources;

import com.stavvy.models.BasicDataResponse;
import com.stavvy.models.EmailPayload;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.services.ses.SesClient;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Optional;

@Path("/email")
public class EmailResource {

    @ConfigProperty(name = "stavvy.email")
    String FROM_EMAIL;

    @Inject
    SesClient ses;

    @POST
    @Path("/send")
    public BasicDataResponse sendEmail(EmailPayload data) {
        String from = Optional.ofNullable(data.getFrom()).orElse(FROM_EMAIL);
        String messageId = ses.sendEmail(req -> req
                .source(from)
                .destination(d -> d.toAddresses(data.getTo()))
                .message(msg -> msg
                        .subject(sub -> sub.data(data.getSubject()))
                        .body(b -> b.text(txt -> txt.data(data.getBody()))))).messageId();

        return BasicDataResponse.builder().detail(messageId).build();
    }

}
