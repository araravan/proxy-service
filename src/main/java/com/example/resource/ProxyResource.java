package com.example.resource;

import com.example.service.HtmlContentModifier;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import io.vertx.core.http.HttpHeaders;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("")
public class ProxyResource {

    @Inject
    HtmlContentModifier htmlContentModifier;

    @Path("{path: .*}")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response proxySite(@PathParam("path") String path) {
        String modifiedContent = htmlContentModifier.modifyContent(path);
        return Response.ok(modifiedContent).header(HttpHeaders.CONTENT_TYPE.toString(), "text/html").build();
    }
}
