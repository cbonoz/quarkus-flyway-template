package com.stavvy.resources;

import com.stavvy.dao.UserDao;
import com.stavvy.models.AppUser;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/users")
public class UserResource {

    @Inject
    UserDao userDao;

    @GET
    public List<AppUser> getUsers() {
        return userDao.getUsers();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        Optional<AppUser> appUserOptional = userDao.find(id);
        if (appUserOptional.isEmpty()) {
            return Response.status(404).build();
        }

        return Response.ok(appUserOptional.get()).build();
    }

    @POST
    public Response insert(AppUser user) {
        userDao.insert(user.getName());
        return Response.ok().build();
    }
}