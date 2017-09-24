package com.myapps.infiquest.dao;

import com.myapps.infiquest.core.Question;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

/**
 * Created by AYellapurkar on 28-11-2016.
 */
public class QuestionDAO extends AbstractDAO<Question>
{

    private QuestionTagDAO questionTagDAO =  null;
    private QuestionUserGroupDAO questionUsergroupDAO = null;

    public QuestionDAO(SessionFactory sessionFactory, QuestionTagDAO questionTagDAO, QuestionUserGroupDAO questionUsergroupDAO) {
        super(sessionFactory);
        this.questionTagDAO = questionTagDAO;
        this.questionUsergroupDAO = questionUsergroupDAO;
    }

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public QuestionDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public List<Question> findAll()
    {

        return list(namedQuery("com.myapps.infiquest.core.Question.findAll"));
    }

    public Optional<Question> findByQuestionId(Long questionId)
    {

        return Optional.ofNullable(
                uniqueResult(namedQuery("com.myapps.infiquest.core.Question.findById")
                        .setParameter("questionId",questionId)

                ));

    }


    public Optional<Question> findByUserId(Long userId)
    {

        return Optional.ofNullable(
                uniqueResult(namedQuery("com.myapps.infiquest.core.Question.findByUserId")
                        .setParameter("userId",userId)

                ));

    }


    //Update and Insert
    public Optional<Question> upsert(Question question)
    {

        /*
        //Populate question_tags relation
        Set<Long> tagIds = question.getTagIds();
        Iterator tagidIterator = tagIds.iterator();
        while(tagidIterator.hasNext())
        {
            QuestionTag qtTag = new QuestionTag(question.getQuestionId(),(Long)tagidIterator.next());
            questionTagDAO.upsert(qtTag);

        }

        //Populate question_usergroups relation
        Set<Long> usergroupIds = question.getUsergroupIds();
        Iterator usergroupIterator = usergroupIds.iterator();
        while(usergroupIterator.hasNext())
        {
            QuestionUsergroup qu = new QuestionUsergroup(question.getQuestionId(),(Long)usergroupIterator.next());
            questionUsergroupDAO.upsert(qu);

        }
*/


        return Optional.ofNullable(persist(question));
    }



    public boolean delete(Long id)
    {
        boolean result = false;
        Question question = this.get(id);
        if(question != null)
        {
            currentSession().delete(question);
            result = true;
        }
        return result;
    }




}
