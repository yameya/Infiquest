package com.myapps.infiquest;

import com.myapps.infiquest.auth.InfiquestAuthenticator;
import com.myapps.infiquest.core.*;
import com.myapps.infiquest.dao.*;
import com.myapps.infiquest.managed.ESClient;
import com.myapps.infiquest.managed.ESClientManager;
import com.myapps.infiquest.resources.*;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;


public class InfiquestApplication extends Application<InfiquestConfiguration>
{

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

    @Override
    public void run(final InfiquestConfiguration configuration, final Environment environment)
    {
        final UsersDAO usersDAO
                = new UsersDAO(hibernateBundle.getSessionFactory());
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

        InfiquestAuthenticator authenticator = new UnitOfWorkAwareProxyFactory(hibernateBundle)
                .create(InfiquestAuthenticator.class);
        authenticator.setUsersDAO(usersDAO);

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<Users>()
                        .setAuthenticator(authenticator)
                        .setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter()));

        ESClient esClient = ESClient.getEsClientInstance(configuration.getElasticSearchHost(),configuration.getElasticSearchPort(),configuration.getElasticSearchClusterName(),configuration.getElasticSearchNodeName());
        ESClientManager esClientManager = new ESClientManager(esClient);
        environment.lifecycle().manage(esClientManager);

        //environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Users.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new UsersResource(usersDAO));
        environment.jersey().register(new UserGroupResource(usergroupDAO));
        environment.jersey().register(new TagsResource(tagsDAO));
        environment.jersey().register(new AnswerResource(answerDAO));
        environment.jersey().register(new QuestionResource(questionDAO));
        environment.jersey().register(new ESResource(esClient));

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


}
