package com.myapps.infiquest;

import com.github.toastshaman.dropwizard.auth.jwt.JwtAuthFilter;
import com.myapps.infiquest.auth.InfiquestAuthenticator;
import com.myapps.infiquest.core.*;
import com.myapps.infiquest.dao.*;
import com.myapps.infiquest.managed.ESClient;
import com.myapps.infiquest.managed.ESClientManager;
import com.myapps.infiquest.resources.*;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.HmacKey;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.WebApplicationException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.EnumSet;

import static org.eclipse.jetty.servlets.CrossOriginFilter.*;


public class InfiquestApplication extends Application<InfiquestConfiguration>
{

    private UsersDAO usersDAO;
    public static void main(final String[] args) throws Exception
    {
        new InfiquestApplication().run(args);
    }

    @Override
    public String getName()
    {
        return "InfiquestApp";
    }

    @Override
    public void initialize(final Bootstrap<InfiquestConfiguration> bootstrap)
    {
        bootstrap.addBundle(hibernateBundle);

    }

    private void setupResources(final Environment environment)
    {
        final UsersDAO usersDAO
                = new UsersDAO(hibernateBundle.getSessionFactory());
        this.usersDAO = usersDAO;

        final UserGroupDAO usergroupDAO
                = new UserGroupDAO(hibernateBundle.getSessionFactory());
        final TagsDAO tagsDAO
                = new TagsDAO(hibernateBundle.getSessionFactory());
        final AnswerDAO answerDAO
                = new AnswerDAO(hibernateBundle.getSessionFactory());
        final QuestionTagDAO questionTagDAO
                = new QuestionTagDAO(hibernateBundle.getSessionFactory());
        final QuestionUserGroupDAO questionUserGroupDAO
                = new QuestionUserGroupDAO(hibernateBundle.getSessionFactory());
        final QuestionDAO questionDAO
                = new QuestionDAO(hibernateBundle.getSessionFactory(),questionTagDAO,questionUserGroupDAO);

        environment.jersey().register(new UsersResource(usersDAO));
        environment.jersey().register(new UserGroupResource(usergroupDAO));
        environment.jersey().register(new TagsResource(tagsDAO));
        environment.jersey().register(new AnswerResource(answerDAO));
        environment.jersey().register(new QuestionResource(questionDAO));

    }


    private void registerElasticSearchResource(final InfiquestConfiguration configuration,final Environment environment)
    {
        ESClient esClient = ESClient.getEsClientInstance(configuration.getElasticSearchHost(),configuration.getElasticSearchPort(),configuration.getElasticSearchClusterName(),configuration.getElasticSearchNodeName());
        ESClientManager esClientManager = new ESClientManager(esClient);
        environment.lifecycle().manage(esClientManager);

        environment.jersey().register(new ESResource(esClient));
    }

    @Override
    public void run(final InfiquestConfiguration configuration, final Environment environment)
    {
        setupResources(environment);
        registerElasticSearchResource(configuration,environment);
        enableCorsHeaders(environment);

        // Authentication related code
        JwtConsumer consumer = setupJWTConsumer(configuration);
        AuthDynamicFeature component = getAuthFilter(consumer,configuration.getAuthMode(),configuration.getAuthRealm());
        environment.jersey().register(component);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Principal.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);

    }



    /**
     * Hibernate bundle.
     */

    private final HibernateBundle<InfiquestConfiguration> hibernateBundle
            = new HibernateBundle<InfiquestConfiguration>(
            Users.class,UserGroup.class,Tags.class, Answer.class, Question.class,QuestionTag.class,QuestionUsergroup.class)
    {
        @Override
        public DataSourceFactory getDataSourceFactory(InfiquestConfiguration configuration)
        {
            return configuration.getDataSourceFactory();
        }
    };

    private void enableCorsHeaders(Environment env)
    {
        final FilterRegistration.Dynamic corsFilter = env.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Add URL mapping
        corsFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        // Configure CORS parameters
        corsFilter.setInitParameter(ALLOWED_ORIGINS_PARAM, "*");
        corsFilter.setInitParameter(ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        corsFilter.setInitParameter(ALLOWED_HEADERS_PARAM, "*");
        corsFilter.setInitParameter(ALLOW_CREDENTIALS_PARAM, "true");

    }

    private JwtConsumer setupJWTConsumer(InfiquestConfiguration configuration)
    {
        JwtConsumer consumer;
        try
        {
            final byte[] key = configuration.getJwtTokenSecret();
            UserService.createInstance(key);

            consumer = new JwtConsumerBuilder()
                    .setRequireExpirationTime() // the JWT must have an expiration time
                    .setRequireSubject() // the JWT must have a subject claim
                    .setVerificationKey(new HmacKey(key)) // verify the signature with the public key
                    .setRelaxVerificationKeyValidation() // relaxes key length requirement
                    .build(); // create the JwtConsumer instance
        }
        catch(UnsupportedEncodingException uex)
        {
            throw new WebApplicationException(uex);
        }

        return consumer;
    }

    private AuthDynamicFeature getAuthFilter(JwtConsumer consumer, String authMode, String authRealm)
    {
        InfiquestAuthenticator authenticator = new UnitOfWorkAwareProxyFactory(hibernateBundle)
                .create(InfiquestAuthenticator.class);
        authenticator.setUsersDAO(usersDAO);

        AuthDynamicFeature component = new AuthDynamicFeature(
                new JwtAuthFilter.Builder<Users>()
                        .setJwtConsumer(consumer)
                        .setRealm(authRealm)
                        .setPrefix(authMode)
                        .setAuthenticator(authenticator)
                        .buildAuthFilter());

        return component;
    }


}
