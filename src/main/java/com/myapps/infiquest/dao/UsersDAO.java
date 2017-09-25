package com.myapps.infiquest.dao;

import com.myapps.infiquest.core.Users;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Optional;

/**
 * Created by AYellapurkar on 28-11-2016.
 */
public class UsersDAO extends AbstractDAO<Users>
{

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public UsersDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public List<Users> findAll()
    {

        return list(namedQuery("com.myapps.infiquest.core.Users.findAll"));
    }

    public Optional<Users> findByName(String name)
    {

        return Optional.ofNullable(
                uniqueResult(namedQuery("com.myapps.infiquest.core.Users.findByName")
                        .setParameter("name",name)

                ));

    }

    public Optional<Users> findByNameAndPwd(String name, String password)
    {
        Optional<Users> optional;
        currentSession().beginTransaction();
        Transaction transaction = currentSession().beginTransaction();
        try {
            // do some transactions
            transaction.commit();
            optional= Optional.ofNullable(
                    uniqueResult(namedQuery("com.myapps.infiquest.core.Users.findByNameAndPwd")
                            .setParameter("name",name)
                            .setParameter("password",password) ));
        } catch (Exception ex) {
            transaction.rollback();
            throw new WebApplicationException(ex);
        }

        return optional;


    }

    public Optional<Users> findByUserId(Long userId)
    {

        return Optional.ofNullable(
                uniqueResult(namedQuery("com.myapps.infiquest.core.Users.findByUserId")
                        .setParameter("userId",userId)


                ));

    }

    //Update and Insert
    public Optional<Users> upsert(Users u)
    {
        return Optional.ofNullable(persist(u));
    }



    public boolean delete(String name, String password)
    {
        boolean result = false;
        Optional<Users> user = this.findByNameAndPwd(name, password);
        if(user.isPresent())
        {
            currentSession().delete(user.get());
            result = true;
        }
        return result;
    }




}
