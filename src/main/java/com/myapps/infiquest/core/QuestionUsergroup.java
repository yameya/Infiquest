package com.myapps.infiquest.core;

import javax.persistence.*;
import javax.security.auth.Subject;
import java.security.Principal;

/**
 * Created by AYellapurkar on 28-11-2016.
 */

@Entity
@Table(name = "usergroup")

public class QuestionUsergroup implements Principal
{
    public QuestionUsergroup()
    {
    }

    @Id
    @Column(name="QU_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long questionUsergroupId;

    @Column(name="QUESTION_ID")
    private Long questionId;

    @Column(name="USERGROUP_ID")
    private Long usergroupId;

    public Long getQuestionUsergroupId() {
        return questionUsergroupId;
    }

    public void setQuestionUsergroupId(Long questionUsergroupId) {
        this.questionUsergroupId = questionUsergroupId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getUsergroupId() {
        return usergroupId;
    }

    public void setUsergroupId(Long usergroupId) {
        this.usergroupId = usergroupId;
    }

    public QuestionUsergroup(Long questionUsergroupId, Long questionId, Long usergroupId) {
        this.questionUsergroupId = questionUsergroupId;
        this.questionId = questionId;
        this.usergroupId = usergroupId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionUsergroup)) return false;

        QuestionUsergroup that = (QuestionUsergroup) o;

        return getQuestionUsergroupId().equals(that.getQuestionUsergroupId());
    }

    @Override
    public int hashCode() {
        return getQuestionUsergroupId().hashCode();
    }

    public QuestionUsergroup(Long questionId, Long usergroupId) {
        this.questionId = questionId;
        this.usergroupId = usergroupId;
    }
}

