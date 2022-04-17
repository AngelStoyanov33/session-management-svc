package me.angelstoyanov.sporton.management.session.client;

import com.fasterxml.jackson.core.type.TypeReference;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.smallrye.common.constraint.NotNull;
import me.angelstoyanov.sporton.management.session.model.Pitch;
import me.angelstoyanov.sporton.management.session.model.PitchType;
import me.angelstoyanov.sporton.management.session.model.User;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@RegisterForReflection
@Named("RestClientWrapper")
public class RestClientWrapper {

    @Inject
    @RestClient
    protected UserManagementClient userManagementClient;

    @Inject
    @RestClient
    protected PitchManagementClient pitchManagementClient;

    public Pitch getPitch(String pitchId) {
        try {
            Response svcResponse = pitchManagementClient.getPitch(pitchId);
            if (svcResponse.getStatus() == Response.Status.OK.getStatusCode()) {
                return svcResponse.readEntity(Pitch.class);
            }
        } catch (WebApplicationException e) {
            return null;
        }
        return null;
    }

    public List<Pitch> getPitchesNearMe(double lat, double lon, double radius, PitchType type) {
        try {
            Response svcResponse = pitchManagementClient.getPitchesNearMe(lat, lon, radius, type);
            if (svcResponse.getStatus() == Response.Status.OK.getStatusCode()) {
                return svcResponse.readEntity(new GenericType<>() {});
            }
        } catch (WebApplicationException e) {
            return null;
        }
        return null;
    }

    public List<Pitch> getPitches(String region, PitchType type) {
        try {
            Response svcResponse = pitchManagementClient.getPitches(region, type);
            if (svcResponse.getStatus() == Response.Status.OK.getStatusCode()) {
                return svcResponse.readEntity(new GenericType<>() {});
            }
        } catch (WebApplicationException e) {
            return null;
        }
        return null;
    }


    public List<User> getUsers(List<String> userIds) {
        try {
            Response svcResponse = userManagementClient.getUsers(userIds);
            if (svcResponse.getStatus() == Response.Status.OK.getStatusCode()) {
                return svcResponse.readEntity(new GenericType<>() {});
            }
        } catch (WebApplicationException e) {
            return null;
        }
        return null;
    }
}
