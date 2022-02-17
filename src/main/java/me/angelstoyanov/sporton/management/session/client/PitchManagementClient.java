package me.angelstoyanov.sporton.management.session.client;

import io.quarkus.runtime.annotations.RegisterForReflection;
import me.angelstoyanov.sporton.management.session.model.Pitch;
import me.angelstoyanov.sporton.management.session.model.PitchType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.List;

@RegisterForReflection
@RegisterRestClient
@ApplicationScoped
public interface PitchManagementClient {

    @GET
    @Path("/pitch/{id}")
    RestResponse<Pitch> getPitch(@PathParam("id") String id);

    @GET
    @Path("/pitch/locate")
    RestResponse<List<Pitch>> getPitchesNearMe(@QueryParam("latitude") double lat, @QueryParam("longitude") double lon,
                                               @QueryParam("radius") double radius, @QueryParam("type") PitchType type);
}