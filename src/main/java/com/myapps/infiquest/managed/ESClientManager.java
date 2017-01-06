package com.myapps.infiquest.managed;

import io.dropwizard.lifecycle.Managed;

/**
 * Created by ayellapurkar on 26-12-2016.
 */
public class ESClientManager implements Managed
{
    private final ESClient esClient;

    public ESClientManager(ESClient esClient)
    {
        this.esClient = esClient;
    }

    @Override
    public void start() throws Exception
    {
        esClient.start();
    }

    @Override
    public void stop() throws Exception
    {
        esClient.stop();
    }
}
