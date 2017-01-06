package com.myapps.infiquest.resources;

import com.myapps.infiquest.core.UserGroup;
import com.myapps.infiquest.dao.UserGroupDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;


/**
 * Created by AYellapurkar on 28-11-2016.
 */
@Path("/usergroup")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserGroupResource
{
    private UserGroupDAO userGroupDAO;

    public UserGroupResource(UserGroupDAO userGroupDAO)
    {
        this.userGroupDAO = userGroupDAO;
    }

    @GET
    @UnitOfWork
    @PermitAll
    public List<UserGroup> findAll()
    {
        return userGroupDAO.findAll();
    }

    @GET
    @Path("/{name}")
    @UnitOfWork
    @PermitAll
    public Optional<UserGroup> findByName(@PathParam("name") Optional<String> name)
    {
        return userGroupDAO.findByName(name.get());
    }

    @POST
    @Path("upsertusergroup")
    @UnitOfWork
    @PermitAll
    public Optional<UserGroup> createUserGroup(UserGroup usergroup)
    {

        return userGroupDAO.upsert(usergroup);
    }

    @DELETE
    @Path("deleteusergroup/{name}")
    @UnitOfWork
    @PermitAll
    public boolean deleteUserGroup(@PathParam("name") Optional<String> name)
    {
        return userGroupDAO.delete(name.get());
    }

}
