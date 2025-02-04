package com.example.client;

import io.quarkus.rest.client.reactive.Url;
import jakarta.ws.rs.GET;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "someuri")
public interface WebPageClient {
    @GET
    String get(@Url String url);
}
