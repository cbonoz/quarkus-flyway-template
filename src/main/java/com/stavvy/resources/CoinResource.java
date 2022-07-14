package com.stavvy.resources;

import com.stavvy.models.BasicDataResponse;
import com.stavvy.models.CoinInfo;
import com.stavvy.services.CoinGeckoService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import java.util.List;

import static com.stavvy.app.Main.APP_NAME;

@Path("/coins")
public class CoinResource {

    @Inject
    @RestClient
    CoinGeckoService coinGeckoService;

    @GET
    @Path("/hello")
    public Response hello() {
        String message = String.format("Hello from %s", APP_NAME);
        BasicDataResponse res = BasicDataResponse.builder().detail(message).build();
        return Response.ok(res).build();
    }

    @GET
    @Path("/list")
    public List<CoinInfo> getCoins() {
        return coinGeckoService.listCoins();
    }
}