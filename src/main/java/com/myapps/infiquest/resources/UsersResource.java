package com.myapps.infiquest.resources;

import com.myapps.infiquest.core.UserService;
import com.myapps.infiquest.core.Users;
import com.myapps.infiquest.dao.UsersDAO;
import io.dropwizard.hibernate.UnitOfWork;
import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;


/**
 * Created by AYellapurkar on 28-11-2016.
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsersResource
{
    private UsersDAO usersDAO;

    public UsersResource(UsersDAO usersDAO)
    {
        this.usersDAO = usersDAO;
    }

    @GET
    @UnitOfWork
    @PermitAll
    public List<Users> findAll()
    {
        return usersDAO.findAll();
    }

    @GET
    @Path("/{name}")
    @UnitOfWork
    @PermitAll
    public Optional<Users> findByName(@PathParam("name") Optional<String> name)
    {
        return usersDAO.findByName(name.get());
    }

    @POST
    @Path("upsertuser")
    @UnitOfWork
    @PermitAll
    public Optional<Users> createUser(Users user)
    {
        return usersDAO.upsert(user);
    }

    @DELETE
    @Path("deleteuser/{name}/{password}")
    @UnitOfWork
    @PermitAll
    public boolean delete(@PathParam("name") Optional<String> name,@PathParam("password") Optional<String> password)
    {
        return usersDAO.delete(name.get(),password.get());
    }

    @POST
    @Path("login")
    @UnitOfWork(transactional = false)

    public Optional<Users> login(Users user)
    {

        String jtoken = "";
        Optional<Users> loggedInUser = usersDAO.findByNameAndPwd(user.getUserName(),user.getUserPassword());
        if(loggedInUser.isPresent())
        {
            Long userId = loggedInUser.get().getUserId();
            jtoken = UserService.getInstance().generateJtokenForUser(userId);
            loggedInUser.get().setUserPassword(jtoken);
        }
        else
        {
            loggedInUser = Optional.empty();
        }

        return loggedInUser;
    }



}
