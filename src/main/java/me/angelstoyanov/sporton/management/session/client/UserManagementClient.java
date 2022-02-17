package me.angelstoyanov.sporton.management.session.client;

import io.quarkus.runtime.annotations.RegisterForReflection;
import me.angelstoyanov.sporton.management.session.model.User;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@RegisterForReflection
@RegisterRestClient
@ApplicationScoped
public interface UserManagementClient {

    @GET
    @Path("/user/{id}")
    RestResponse<User> getUser(@PathParam("id") String id);

    @GET
    @Path("/users")
    RestResponse<List<User>> getUsers(List<String> ids);
}
