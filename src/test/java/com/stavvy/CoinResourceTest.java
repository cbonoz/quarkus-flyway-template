package com.stavvy;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CoinResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/coins/hello")
          .then()
             .statusCode(200)
             .body("detail", equalTo("Hello from vault-api"));
    }

}