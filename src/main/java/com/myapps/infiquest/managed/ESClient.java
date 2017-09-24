package com.myapps.infiquest.managed;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.myapps.infiquest.core.Question;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.WebApplicationException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

/**
 * Created by ayellapurkar on 26-12-2016.
 */
public class ESClient
{

    private static ESClient esClientInstance = null;
    private static final String titleColumn = "question_title";
    private static final String descColumn = "question_description";
    private static final String searchIndex = "questions";

    TransportClient client = null;
    String hostName;
    String port;
    String clusterName;
    String nodeName;

    private ESClient(String hostName, String port,String clusterName, String nodeName)
    {
        this.hostName = hostName;
        this.port = port;
        this.clusterName = clusterName;
        this.nodeName = nodeName;
    }

    public static ESClient getEsClientInstance(String hostName, String port,String clusterName, String nodeName)
    {
        if(esClientInstance == null)
        {
            esClientInstance = new ESClient(hostName,port,clusterName,nodeName);
        }

        return esClientInstance;

    }

    public void start()
    {
        if(client == null)
        {
            try
            {
                Settings settings = Settings.builder().put("cluster.name", clusterName)
                        .put("node.name",nodeName).build();
                client = new PreBuiltTransportClient(settings)
                        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostName), Integer.parseInt(port)));

            }
            catch (UnknownHostException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void stop()
    {
        if(client != null)
        {
            this.client.close();
        }
    }


    public List<Question> searchQuestions(String searchText)
    {
        QueryBuilder qb = multiMatchQuery(searchText,titleColumn,descColumn);

        SearchResponse searchResponse = client.prepareSearch(searchIndex)
                .setQuery(qb)
                .execute().actionGet();

        SearchHits hits = searchResponse.getHits();

        ObjectMapper mapper = new ObjectMapper();
        List<Question> questionList = new ArrayList<Question>();

        for (SearchHit hit : hits)
        {
            try
            {
                Question quesobj = mapper.readValue(hit.sourceAsString(), Question.class);
                questionList.add(quesobj);

            }
            catch(Exception ex)
            {
                throw new WebApplicationException(ex);
            }

        }
        return questionList;
    }




}
