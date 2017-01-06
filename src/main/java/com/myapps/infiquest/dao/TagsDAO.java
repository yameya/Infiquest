package com.myapps.infiquest.dao;

import com.myapps.infiquest.core.Tags;
import com.myapps.infiquest.core.UserGroup;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

/**
 * Created by AYellapurkar on 28-11-2016.
 */
public class TagsDAO extends AbstractDAO<Tags>
{

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public TagsDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public List<Tags> findAll()
    {

        return list(namedQuery("com.myapps.infiquest.core.Tags.findAll"));
    }

    public Optional<Tags> findByName(String name)
    {

        return Optional.ofNullable(
                uniqueResult(namedQuery("com.myapps.infiquest.core.Tags.findByName")
                        .setParameter("name",name)

                ));

    }



    //Update and Insert
    public Optional<Tags> upsert(Tags t)
    {
        return Optional.ofNullable(persist(t));
    }



    public boolean delete(String name)
    {
        boolean result = false;
        Optional<Tags> tag = this.findByName(name);
        if(tag.isPresent())
        {
            currentSession().delete(tag.get());
            result = true;
        }
        return result;
    }




}
