package com.example;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@QuarkusTest
class ProxyResourceIT {

    private static final int MOCK_SERVER_PORT = 8089;
    private static final String IMAGE_URL = "/assets/images/about/icon-versatility.svg";
    private static WireMockServer wireMockServer;

    @BeforeAll
    static void startMockServer() {
        wireMockServer = new WireMockServer(MOCK_SERVER_PORT);
        wireMockServer.start();
        WireMock.configureFor("localhost", MOCK_SERVER_PORT);
    }

    @AfterAll
    static void stopMockServer() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    @Test
    @Order(1)
    public void testProxyTextModification() {
        stubFor(get(urlEqualTo("/quarkus3"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/html")
                        .withBody("<html><body><p>Built on a robust™ reactive core</p></body></html>")));

        given()
            .when().get("/quarkus3")
            .then()
            .statusCode(200)
            .contentType("text/html")
            .body(containsString("robust™"));
    }

    @Test
    @Order(2)
    public void testProxyInternalImageRewriting() {
        stubFor(get(urlEqualTo("/quarkus3"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/html")
                        .withBody("<html><body><img src=\"" + IMAGE_URL + "\"></body></html>")));

        given()
            .when().get("/quarkus3")
            .then()
            .statusCode(200)
            .contentType("text/html")
            .body(containsString("http://localhost:" + MOCK_SERVER_PORT + IMAGE_URL));
    }
}
