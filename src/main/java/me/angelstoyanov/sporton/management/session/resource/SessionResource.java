package me.angelstoyanov.sporton.management.session.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.smallrye.common.constraint.NotNull;
import me.angelstoyanov.sporton.management.session.client.RestClientWrapper;
import me.angelstoyanov.sporton.management.session.exception.SessionNotExistsException;
import me.angelstoyanov.sporton.management.session.model.*;
import me.angelstoyanov.sporton.management.session.model.dto.PitchDTO;
import me.angelstoyanov.sporton.management.session.model.dto.UserDTO;
import me.angelstoyanov.sporton.management.session.model.extended.ExtendedSession;
import me.angelstoyanov.sporton.management.session.model.extended.ExtendedSessionUser;
import me.angelstoyanov.sporton.management.session.repository.SessionRepository;
import org.bson.types.ObjectId;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SessionResource {

    @Inject
    protected SessionRepository sessionRepository;

    @Inject
    protected RestClientWrapper restClient;

    @Inject
    protected ObjectMapper objectMapper;

    @POST
    @ResponseStatus(201)
    @Path("/session")
    public RestResponse<Session> createPitch(Session session) {
        Session newSession = sessionRepository.addSession(session);
        return RestResponse.ResponseBuilder.ok(newSession).build();

    }

    @GET
    @ResponseStatus(200)
    @Path("/session/{id}")
    public RestResponse<String> getSession(@PathParam("id") String id, @QueryParam("output") String output) throws JsonProcessingException {
        Session session = sessionRepository.findById(new ObjectId(id));
        if (session == null) {
            return RestResponse.ResponseBuilder.ok((String) null).status(RestResponse.Status.NOT_FOUND).build();
        }
        if (output != null && output.equalsIgnoreCase("wide")) {
            List<String> userIds = new LinkedList<>();
            List<ExtendedSessionUser> extendedSessionUsers = new LinkedList<>();
            session.getUsers().forEach(sessionUser -> userIds.add(sessionUser.getUserId().toString()));
            AtomicReference<UserDTO> organizerUser = new AtomicReference<>();
            restClient.getUsers(userIds).forEach(user -> {
                SessionUser sessionUserReference = session.getUsers()
                        .stream()
                        .filter(sessionUser -> sessionUser.getUserId().equals(user.getId()))
                        .findFirst().get();
                extendedSessionUsers.add(new ExtendedSessionUser(new UserDTO(user),
                        sessionUserReference.getJoinedAt(), sessionUserReference.getLeftAt()));
                if (user.getId().equals(session.getOrganizerId())) {
                    organizerUser.set(new UserDTO(user));
                }
            });
            ExtendedSession extendedSession = new ExtendedSession(session,
                    extendedSessionUsers, organizerUser.get(), new PitchDTO(restClient.getPitch(session.getPitchId().toString())));
            return RestResponse.ResponseBuilder.ok(objectMapper.writeValueAsString(extendedSession)).build();
        } else {
            return RestResponse.ResponseBuilder.ok(objectMapper.writeValueAsString(session)).build();
        }
    }


    @DELETE
    @ResponseStatus(200)
    @Path("/session/{id}")
    public RestResponse<Session> deletePitch(@PathParam("id") String id) {
        try {
            sessionRepository.deleteSessionById(new ObjectId(id));
            return RestResponse.ResponseBuilder.ok((Session) null).build();
        } catch (SessionNotExistsException e) {
            return RestResponse.ResponseBuilder.ok((Session) null).status(RestResponse.Status.NOT_FOUND).build();
        }
    }

    @GET
    @ResponseStatus(200)
    @Path("/session/locate")
    public RestResponse<List<Session>> locateSessions(@NotNull @DefaultValue("ACTIVE") @QueryParam("status") SessionStatus status,
                                                      @QueryParam("latitude") Double lat, @QueryParam("longitude") Double lon,
                                                      @QueryParam("radius") Double radius, @QueryParam("type") PitchType type,
                                                      @QueryParam("region") String region) {
        List<Session> sessions = new LinkedList<>();
        List<Pitch> pitches = new LinkedList<>();
        if (lat != null && lon != null && radius != null) {
            pitches = restClient.getPitchesNearMe(lat, lon, radius, type);
        } else if (region != null) {
            pitches = restClient.getPitches(region, type);
        }

        if (pitches != null) {
            pitches.forEach(pitch -> {
                Session sessionOnCurrentPitch = sessionRepository.getSessionByPitchIdAndStatus(pitch.getId().toString(), status);
                if (sessionOnCurrentPitch != null) {
                    sessions.add(sessionOnCurrentPitch);
                }
            });
        }

        return RestResponse.ResponseBuilder.ok(sessions).build();

    }

}
