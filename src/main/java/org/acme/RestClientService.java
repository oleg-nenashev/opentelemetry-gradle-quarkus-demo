package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/")
@RegisterRestClient(configKey = "hackernews-api-server")
@Produces(MediaType.APPLICATION_JSON)
public interface RestClientService {

    @GET
    @Path("/beststories.json")
    Multi<String> getBestStories();

    @GET
    @Path("/item/{id}.json")
    Uni<HackerNewsItem> getItem(@PathParam("id") String id);

}
