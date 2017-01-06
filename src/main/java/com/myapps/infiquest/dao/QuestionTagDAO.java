package com.myapps.infiquest.dao;

import com.myapps.infiquest.core.Question;
import com.myapps.infiquest.core.QuestionTag;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by AYellapurkar on 28-11-2016.
 */
public class QuestionTagDAO extends AbstractDAO<QuestionTag>
{

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public QuestionTagDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    //Update and Insert
    public Optional<QuestionTag> upsert(QuestionTag questionTag)
    {

        return Optional.ofNullable(persist(questionTag));
    }



    public boolean delete(Long id)
    {
        boolean result = false;
        QuestionTag questionTag = this.get(id);
        if(questionTag != null)
        {
            currentSession().delete(questionTag);
            result = true;
        }
        return result;
    }




}
