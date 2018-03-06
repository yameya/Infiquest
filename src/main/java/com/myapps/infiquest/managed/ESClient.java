package com.myapps.infiquest.managed;

import com.myapps.infiquest.core.Question;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
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
    private static final String searchType = "question";
    private static final String creationTimeColumn = "question_timestamp";
    private static final String upvotesColumn = "question_upvotes";
    private static final String downvotesColumn = "question_downvotes";
    private static final String questionIdColumn = "question_id";

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
                //TODO - Make this throw exception
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

    public void createQuestionDoc(Question question)
    {
           try
           {

           IndexResponse response = client.prepareIndex(searchIndex, searchType,String.valueOf(question.getQuestionId()))
                                     .setSource(jsonBuilder()
                                     .startObject()
                                     .field(titleColumn, question.getQuestionTitle())
                                     .field(descColumn, question.getQuestionDescription())
                                     .field(creationTimeColumn,question.getQuestionCreationTime())
                                     .field(upvotesColumn,question.getQuestionUpvotes())
                                     .field(downvotesColumn,question.getQuestionDownvotes())
                                     .field(questionIdColumn,question.getQuestionId())

                                     .endObject())
                                     .get();

            RestStatus status = response.status();
            if(status.getStatus() == 201)
            {
                // TODO: Add logger
            }
            else
            {
                throw new WebApplicationException(status.toString());
            }


        }
        catch(IOException ex)
        {
            throw new WebApplicationException(ex);
        }

    }




}
