package me.angelstoyanov.sporton.management.session.client;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.smallrye.common.constraint.NotNull;
import me.angelstoyanov.sporton.management.session.model.PitchType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@RegisterForReflection
@RegisterRestClient
@ApplicationScoped
public interface PitchManagementClient {

    @GET
    @Path("/pitch/{id}")
    Response getPitch(@PathParam("id") String id);

    @GET
    @Path("/pitch/locate")
    Response getPitchesNearMe(@QueryParam("latitude") double lat, @QueryParam("longitude") double lon,
                              @QueryParam("radius") double radius, @QueryParam("type") PitchType type);

    @GET
    @Path("/pitches")
    Response getPitches(@NotNull @QueryParam("region") String region,
                        @QueryParam("type") PitchType type);

}