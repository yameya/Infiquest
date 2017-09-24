package com.myapps.infiquest.dao;

import com.myapps.infiquest.core.Answer;
import com.myapps.infiquest.core.Tags;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

/**
 * Created by AYellapurkar on 28-11-2016.
 */
public class AnswerDAO extends AbstractDAO<Answer>
{

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public AnswerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public List<Answer> findAll()
    {

        return list(namedQuery("com.myapps.infiquest.core.Answer.findAll"));
    }

    public List<Answer> findByQuestionId(Long questionId)
    {

        return  list(namedQuery("com.myapps.infiquest.core.Answer.findByQuestionId")
                        .setParameter("questionId",questionId)

                );

    }


    public Optional<Answer> findByUserId(Long userId)
    {

        return Optional.ofNullable(
                uniqueResult(namedQuery("com.myapps.infiquest.core.Answer.findByUserId")
                        .setParameter("userId",userId)

                ));

    }


    //Update and Insert
    public Optional<Answer> upsert(Answer answer)
    {
        return Optional.ofNullable(persist(answer));
    }



    public boolean delete(Long id)
    {
        boolean result = false;
        Answer answer = this.get(id);
        if(answer != null)
        {
            currentSession().delete(answer);
            result = true;
        }
        return result;
    }




}
