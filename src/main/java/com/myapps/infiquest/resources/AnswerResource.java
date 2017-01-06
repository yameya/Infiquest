package com.myapps.infiquest.resources;

import com.myapps.infiquest.core.Answer;
import com.myapps.infiquest.core.Users;
import com.myapps.infiquest.dao.AnswerDAO;
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
@Path("/answers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnswerResource
{
    private AnswerDAO answerDAO;

    public AnswerResource(AnswerDAO answerDAO)
    {
        this.answerDAO = answerDAO;
    }

    @GET
    @UnitOfWork
    @PermitAll
    public List<Answer> findAll()
    {
        return answerDAO.findAll();
    }

    @GET
    @Path("getByQuestionId/{questionId}")
    @UnitOfWork
    @PermitAll
    public Optional<Answer> findByQuestionId(@PathParam("questionId") Optional<Long> questionId)
    {
        return answerDAO.findByQuestionId(questionId.get());
    }

    @GET
    @Path("getByUserId/{userId}")
    @UnitOfWork
    @PermitAll
    public Optional<Answer> findByUserId(@PathParam("userId") Optional<Long> userId)
    {
        return answerDAO.findByQuestionId(userId.get());
    }

    @POST
    @Path("upsertanswer")
    @UnitOfWork
    @PermitAll
    public Optional<Answer> createAnswer(Answer answer)
    {

        return answerDAO.upsert(answer);
    }

    @DELETE
    @Path("deleteanswer/{id}/")
    @UnitOfWork
    @PermitAll
    public boolean deleteAnswer(@PathParam("id") Optional<Long> id)
    {
        return answerDAO.delete(id.get());
    }

}
