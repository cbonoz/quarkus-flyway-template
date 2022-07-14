package com.stavvy.services;

import com.stavvy.models.CoinInfo;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

//        http://www.mastertheboss.com/soa-cloud/quarkus/how-to-code-a-quarkus-rest-client/
@Path("/v3")
@RegisterRestClient
@ApplicationScoped
public interface CoinGeckoService{
//    https://www.coingecko.com/en/api/documentation

    @GET
    @Path("/coins/list")
    List<CoinInfo> listCoins();
}