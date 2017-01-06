package com.myapps.infiquest.resources;


import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.myapps.infiquest.core.Question;
import com.myapps.infiquest.managed.ESClient;

import java.util.List;
import java.util.Optional;


/**
 * Created by ayellapurkar on 14-12-2016.
 */
@Path("/searchquestions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ESResource
{

    private ESClient esClient;

    public ESResource(ESClient esClient)
    {
        this.esClient = esClient;
    }

    @GET
    @Path("{searchText}")
    @PermitAll
    public List<Question> search(@PathParam("searchText") Optional<String> searchText )
    {
        return esClient.searchQuestions(searchText.get());
    }


}
