package com.myapps.infiquest;


import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

public class InfiquestConfiguration extends Configuration {

    /**
     * A factory used to connect to a relational database management system.
     * Factories are used by Dropwizard to group together related configuration
     * parameters such as database connection driver, URI, password etc.
     */

    private DataSourceFactory dataSourceFactory
            = new DataSourceFactory();


    /**
     * A getter for the database factory.
     *
     * @return An instance of database factory deserialized from the
     * configuration file passed as a command-line argument to the application.
     */
    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory()
    {
        return dataSourceFactory;
    }

    @NotEmpty
    @JsonProperty
    private String elasticSearchHost = "127.0.0.1";

    @NotEmpty
    @JsonProperty
    private String elasticSearchPort = "9300";

    @NotEmpty
    @JsonProperty
    private String elasticSearchClusterName = "elasticSearch";

    @NotEmpty
    @JsonProperty
    private String elasticSearchNodeName = "infiquestnode";

    public String getElasticSearchHost() {
        return elasticSearchHost;
    }

    public void setElasticSearchHost(String elasticSearchHost) {
        this.elasticSearchHost = elasticSearchHost;
    }

    public String getElasticSearchPort() {
        return elasticSearchPort;
    }

    public void setElasticSearchPort(String elasticSearchPort) {
        this.elasticSearchPort = elasticSearchPort;
    }

    public String getElasticSearchClusterName() {
        return elasticSearchClusterName;
    }

    public void setElasticSearchClusterName(String elasticSearchClusterName) {
        this.elasticSearchClusterName = elasticSearchClusterName;
    }

    public String getElasticSearchNodeName() {
        return elasticSearchNodeName;
    }

    public void setElasticSearchNodeName(String elasticSearchNodeName) {
        this.elasticSearchNodeName = elasticSearchNodeName;
    }
}
