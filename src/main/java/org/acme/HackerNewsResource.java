package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestPath;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/hackernews")
@ApplicationScoped
public class HackerNewsResource {

    @RestClient
    RestClientService serviceToBeMocked;

    @GET
    @Path("/beststories.json")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<String> bestStories() {
        return serviceToBeMocked.getBestStories();
    }

    @GET
    @Path("/item/{id}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<HackerNewsItem> item(@RestPath String id) {
        return serviceToBeMocked.getItem(id);
    }

    @GET
    @Path("/user/{id}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<String> user(@RestPath String id) {
        return serviceToBeMocked.getUser(id);
    }
}

