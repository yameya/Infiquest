package com.myapps.infiquest.resources;

import com.myapps.infiquest.core.Tags;
import com.myapps.infiquest.core.UserGroup;
import com.myapps.infiquest.dao.TagsDAO;
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
@Path("/tags")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TagsResource
{
    private TagsDAO tagsDAO;

    public TagsResource(TagsDAO usersDAO)
    {
        this.tagsDAO = usersDAO;
    }

    @GET
    @UnitOfWork
    @PermitAll
    public List<Tags> findAll()
    {
        return tagsDAO.findAll();
    }

    @GET
    @Path("/{name}")
    @UnitOfWork
    @PermitAll
    public Optional<Tags> findByName(@PathParam("name") Optional<String> name)
    {
        return tagsDAO.findByName(name.get());
    }

    @POST
    @Path("upserttag")
    @UnitOfWork
    @PermitAll
    public Optional<Tags> createTag(Tags tag)
    {

        return tagsDAO.upsert(tag);
    }

    @DELETE
    @Path("deletetag/{name}")
    @UnitOfWork
    @PermitAll
    public boolean deleteTag(@PathParam("name") Optional<String> name)
    {
        return tagsDAO.delete(name.get());
    }

}
