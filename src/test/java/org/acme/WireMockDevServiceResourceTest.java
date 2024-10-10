package org.acme;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.client.WireMock;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkiverse.wiremock.devservice.ConnectWireMock;

@QuarkusTest
@ConnectWireMock
class WireMockDevServiceResourceTest {

    WireMock wiremock;

    @Test
    void testHelloEndpoint() {
        Assertions.assertNotNull(wiremock);
        
        given().when().get("/hello").then()
            .statusCode(200)
            .body(containsString("Hello from RESTEasy"));
    }

    @Test
    void testBestStories() {
        Assertions.assertNotNull(wiremock);
        
        given().when().get("/hackernews/beststories.json").then()
            .statusCode(200)
            .body(containsString("12346"));
    }
}



