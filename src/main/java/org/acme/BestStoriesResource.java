package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import io.smallrye.mutiny.Uni;

@Path("/")
@ApplicationScoped
public class BestStoriesResource {

    @RestClient
    RestClientService serviceToBeMocked;

    @GET
    @Path("/beststories")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> bestStories() {
        return serviceToBeMocked.get(); // this call needs to be mocked
    }
}

