package com.myapps.infiquest.auth;
import com.myapps.infiquest.core.Users;
import com.myapps.infiquest.dao.UsersDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;

/**
 * Created by AYellapurkar on 02-12-2016.
 */
public class InfiquestAuthenticator implements Authenticator<BasicCredentials, Users>
{
    private UsersDAO usersDAO = null;


    public InfiquestAuthenticator()
    {

    }

   public void setUsersDAO(UsersDAO usersDAO)
   {
       this.usersDAO = usersDAO;
   }


    @Override
    @UnitOfWork
    public java.util.Optional<Users> authenticate(BasicCredentials credentials) throws AuthenticationException
    {
        return usersDAO.findByNameAndPwd(credentials.getUsername(),credentials.getPassword());
    }
}
