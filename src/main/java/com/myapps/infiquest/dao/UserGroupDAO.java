package com.myapps.infiquest.dao;

import com.myapps.infiquest.core.UserGroup;
import com.myapps.infiquest.core.Users;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

/**
 * Created by AYellapurkar on 28-11-2016.
 */
public class UserGroupDAO extends AbstractDAO<UserGroup>
{

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public UserGroupDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public List<UserGroup> findAll()
    {

        return list(namedQuery("com.myapps.infiquest.core.UserGroup.findAll"));
    }

    public Optional<UserGroup> findByName(String name)
    {

        return Optional.ofNullable(
                uniqueResult(namedQuery("com.myapps.infiquest.core.UserGroup.findByName")
                        .setParameter("name",name)

                ));

    }



    //Update and Insert
    public Optional<UserGroup> upsert(UserGroup u)
    {
        return Optional.ofNullable(persist(u));
    }



    public boolean delete(String name)
    {
        boolean result = false;
        Optional<UserGroup> usergroup = this.findByName(name);
        if(usergroup.isPresent())
        {
            currentSession().delete(usergroup.get());
            result = true;
        }
        return result;
    }




}
