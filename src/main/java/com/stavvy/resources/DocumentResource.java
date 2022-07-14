package com.stavvy.resources;

import com.stavvy.models.BasicDataResponse;
import com.stavvy.services.FileUploadService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/documents")
public class DocumentResource {


    @Inject
    FileUploadService fileUploadService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response fileUpload(@MultipartForm MultipartFormDataInput input) {
//        String result = fileUploadService.uploadFile(input);
        return Response.ok().entity(BasicDataResponse.builder().detail(input.getPreamble()).build()).build();
    }

}