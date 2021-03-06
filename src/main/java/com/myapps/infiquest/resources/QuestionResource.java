package com.myapps.infiquest.resources;

import com.myapps.infiquest.core.Answer;
import com.myapps.infiquest.core.Question;
import com.myapps.infiquest.dao.AnswerDAO;
import com.myapps.infiquest.dao.QuestionDAO;
import com.myapps.infiquest.managed.ESClient;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;


/**
 * Created by AYellapurkar on 28-11-2016.
 */
@Path("/questions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionResource
{
    private ESClient esClient;

    private QuestionDAO questionDAO;

    public QuestionResource(ESClient esClient,QuestionDAO questionDAO)
    {
        this.esClient = esClient;
        this.questionDAO = questionDAO;
    }

    @GET
    @UnitOfWork
    @PermitAll
    public List<Question> findAll()
    {
        return questionDAO.findAll();
    }

    @GET
    @Path("getByQuestionId/{questionId}")
    @UnitOfWork
    @PermitAll
    public Optional<Question> findByQuestionId(@PathParam("questionId") Optional<Long> questionId)
    {
        return questionDAO.findByQuestionId(questionId.get());
    }

    @GET
    @Path("getByUserId/{userId}")
    @UnitOfWork
    @PermitAll
    public Optional<Question> findByUserId(@PathParam("userId") Optional<Long> userId)
    {
        return questionDAO.findByQuestionId(userId.get());
    }

    @POST
    @Path("upsertquestion")
    @UnitOfWork
    @PermitAll
    public Optional<Question> createQuestion(Question question)
    {

        Optional<Question> optional = questionDAO.upsert(question);
        if(optional.isPresent())
        {
            esClient.createQuestionDoc(question);
        }

        return optional;
    }

    @DELETE
    @Path("deletequestion/{id}/")
    @UnitOfWork
    @PermitAll
    public boolean deleteQuestion(@PathParam("id") Optional<Long> id)
    {
        return questionDAO.delete(id.get());
    }

}
