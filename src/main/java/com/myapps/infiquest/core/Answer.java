package com.myapps.infiquest.core;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.security.auth.Subject;
import java.security.Principal;

/**
 * Created by AYellapurkar on 28-11-2016.
 */

@Entity
@Table(name = "answer")
@NamedQueries({
        @NamedQuery(name = "com.myapps.infiquest.core.Answer.findAll",
                query = "select a from Answer a"),
        @NamedQuery(name = "com.myapps.infiquest.core.Answer.findByQuestionId",
                query = "select a from Answer a where a.questionId = :questionId"),
        @NamedQuery(name = "com.myapps.infiquest.core.Answer.findByUserId",
                query = "select a from Answer a where a.userId = :userId")

})

public class Answer implements Principal
{
    public Answer() {
    }

    @Id
    @Column(name="ANSWER_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long answerId;

    @Column(name="ANSWER_CONTENT")
    private String answerContent;

    @Column(name="QUESTION_ID")
    private Long questionId;

    @Column(name="USER_ID")
    private Long userId;

    @Column(name = "ANSWER_TIMESTAMP")
    private Long answerCreationTime;

    @Column(name = "ANSWER_UPVOTES")
    private Long answerUpvotes;

    @Column(name = "ANSWER_DOWNVOTES")
    private Long answerDownvotes;

    public Answer(Long answerId, String answerContent, Long questionId, Long userId, Long answerCreationTime, Long answerUpvotes, Long answerDownvotes) {
        this.answerId = answerId;
        this.answerContent = answerContent;
        this.questionId = questionId;
        this.userId = userId;
        this.answerCreationTime = answerCreationTime;
        this.answerUpvotes = answerUpvotes;
        this.answerDownvotes = answerDownvotes;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAnswerCreationTime() {
        return answerCreationTime;
    }

    public void setAnswerCreationTime(Long answerCreationTime) {
        this.answerCreationTime = answerCreationTime;
    }

    public Long getAnswerUpvotes() {
        return answerUpvotes;
    }

    public void setAnswerUpvotes(Long answerUpvotes) {
        this.answerUpvotes = answerUpvotes;
    }

    public Long getAnswerDownvotes() {
        return answerDownvotes;
    }

    public void setAnswerDownvotes(Long answerDownvotes) {
        this.answerDownvotes = answerDownvotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;

        Answer answer = (Answer) o;

        return getAnswerId().equals(answer.getAnswerId());
    }

    @Override
    public int hashCode() {
        return getAnswerId().hashCode();
    }

    @Override
    @Transient
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
