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
@Table(name = "questiontag")

public class QuestionTag implements Principal
{
    public QuestionTag()
    {
    }

    @Id
    @Column(name="QT_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long questionTagId;

    @Column(name="QUESTION_ID")
    private Long questionId;

    @Column(name="TAG_ID")
    private Long tagId;


    public QuestionTag(Long questionTagId, Long questionId, Long tagId) {
        this.questionTagId = questionTagId;
        this.questionId = questionId;
        this.tagId = tagId;
    }

    public Long getQuestionTagId() {
        return questionTagId;
    }

    public void setQuestionTagId(Long questionTagId) {
        this.questionTagId = questionTagId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionTag)) return false;

        QuestionTag that = (QuestionTag) o;

        return getQuestionTagId().equals(that.getQuestionTagId());
    }

    @Override
    public int hashCode() {
        return getQuestionTagId().hashCode();
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

    public QuestionTag(Long questionId, Long tagId) {
        this.questionId = questionId;
        this.tagId = tagId;
    }
}

