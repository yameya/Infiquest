package com.myapps.infiquest.auth;
import com.myapps.infiquest.core.Users;
import com.myapps.infiquest.dao.UsersDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.JwtContext;

import javax.ws.rs.WebApplicationException;
import java.util.Optional;

/**
 * Created by AYellapurkar on 02-12-2016.
 */

public class InfiquestAuthenticator implements Authenticator<JwtContext, Users>
{
    private UsersDAO usersDAO = null;


    public InfiquestAuthenticator()
    {

    }

    @Override
    @UnitOfWork
    public Optional<Users> authenticate(JwtContext context) {
        try {
            final String subject = context.getJwtClaims().getSubject();
            return usersDAO.findByUserId(Long.parseLong(subject));
        } catch (MalformedClaimException ex) {
            throw new WebApplicationException(ex);
        }

    }

    public void setUsersDAO(UsersDAO usersDAO)
    {
        this.usersDAO = usersDAO;
    }


}
