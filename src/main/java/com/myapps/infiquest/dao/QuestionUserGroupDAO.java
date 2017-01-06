package com.myapps.infiquest.dao;

import com.myapps.infiquest.core.QuestionTag;
import com.myapps.infiquest.core.QuestionUsergroup;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Optional;

/**
 * Created by AYellapurkar on 28-11-2016.
 */
public class QuestionUserGroupDAO extends AbstractDAO<QuestionUsergroup>
{

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public QuestionUserGroupDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    //Update and Insert
    public Optional<QuestionUsergroup> upsert(QuestionUsergroup questionUsergroup)
    {

        return Optional.ofNullable(persist(questionUsergroup));
    }



    public boolean delete(Long id)
    {
        boolean result = false;
        QuestionUsergroup questionUsergroup = this.get(id);
        if(questionUsergroup != null)
        {
            currentSession().delete(questionUsergroup);
            result = true;
        }
        return result;
    }




}
