package me.angelstoyanov.sporton.management.session.resource;

import me.angelstoyanov.sporton.management.session.client.PitchManagementClient;
import me.angelstoyanov.sporton.management.session.client.UserManagementClient;
import me.angelstoyanov.sporton.management.session.exception.SessionNotExistsException;
import me.angelstoyanov.sporton.management.session.model.Session;
import me.angelstoyanov.sporton.management.session.repository.SessionRepository;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SessionResource {

    @Inject
    protected SessionRepository sessionRepository;

    @Inject
    @RestClient
    protected UserManagementClient userManagementClient;

    @Inject
    @RestClient
    protected PitchManagementClient pitchManagementClient;

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
    public RestResponse<Session> getSession(@PathParam("id") String id) {
        Session session = sessionRepository.findById(new ObjectId(id));
        if (session == null) {
            return RestResponse.ResponseBuilder.ok((Session) null).status(RestResponse.Status.NOT_FOUND).build();
        }
        return RestResponse.ResponseBuilder.ok(session).build();
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

}
