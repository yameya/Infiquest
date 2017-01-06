package com.myapps.infiquest.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.security.auth.Subject;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by AYellapurkar on 28-11-2016.
 */

@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
@Table(name = "question")
@NamedQueries({
        @NamedQuery(name = "com.myapps.infiquest.core.Question.findAll",
                query = "select q from Question q"),
        @NamedQuery(name = "com.myapps.infiquest.core.Question.findById",
                query = "select q from Question q where q.questionId = :questionId"),
        @NamedQuery(name = "com.myapps.infiquest.core.Question.findByUserId",
                query = "select q from Question q where q.userId = :userId")

})

public class Question implements Principal
{


    public Question()
    {
    }

    @Id
    @Column(name="QUESTION_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonProperty("question_id")
    private Long questionId;

    @JsonProperty("question_description")
    @Column(name="QUESTION_DESCRIPTION")
    private String questionDescription;

    @JsonProperty("question_title")
    @Column(name="QUESTION_TITLE")
    private String questionTitle;

    @JsonProperty("question_timestamp")
    @Column(name = "QUESTION_TIMESTAMP")
    private Long questionCreationTime;

    @JsonProperty("question_upvotes")
    @Column(name = "QUESTION_UPVOTES")
    private Long questionUpvotes;

    @JsonProperty("question_downvotes")
    @Column(name = "QUESTION_DOWNVOTES")
    private Long questionDownvotes;

    @JsonProperty("user_id")
    @Column(name ="USER_ID")
    private Long userId;

    @ManyToMany(targetEntity = Tags.class)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "QUESTIONTAG",
            joinColumns = @JoinColumn(name="QUESTION_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    Set<Tags> tags = new HashSet<>();

    @ManyToMany(targetEntity = UserGroup.class)
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "QUESTIONUSERGROUP",
            joinColumns = @JoinColumn(name="QUESTION_ID"),
            inverseJoinColumns = @JoinColumn(name = "USERGROUP_ID"))
    Set<UserGroup> usergroups = new HashSet<>();

    @Transient
    private Set<Long>  tagIds = new HashSet<>();

    @Transient
    private Set<Long> usergroupIds = new HashSet<>();

    public Set<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(Set<Long> tagIds) {
        this.tagIds = tagIds;
    }

    public Set<Long> getUsergroupIds() {
        return usergroupIds;
    }

    public void setUsergroupIds(Set<Long> usergroupIds) {
        this.usergroupIds = usergroupIds;
    }

    public Set<UserGroup> getUsergroups() {
        return usergroups;
    }

    public void setUsergroups(Set<UserGroup> usergroups) {
        this.usergroups = usergroups;
    }

    public Set<Tags> getTags() {
        return tags;
    }

    public void setTags(Set<Tags> tags) {
        this.tags = tags;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Long getQuestionCreationTime() {
        return questionCreationTime;
    }

    public void setQuestionCreationTime(Long questionCreationTime) {
        this.questionCreationTime = questionCreationTime;
    }

    public Long getQuestionUpvotes() {
        return questionUpvotes;
    }

    public void setQuestionUpvotes(Long questionUpvotes) {
        this.questionUpvotes = questionUpvotes;
    }

    public Long getQuestionDownvotes() {
        return questionDownvotes;
    }

    public void setQuestionDownvotes(Long questionDownvotes) {
        this.questionDownvotes = questionDownvotes;
    }

    public Question(Long questionId, String questionDescription, String questionTitle, Long questionCreationTime, Long questionUpvotes, Long questionDownvotes) {
        this.questionId = questionId;
        this.questionDescription = questionDescription;
        this.questionTitle = questionTitle;
        this.questionCreationTime = questionCreationTime;
        this.questionUpvotes = questionUpvotes;
        this.questionDownvotes = questionDownvotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        return getQuestionId().equals(question.getQuestionId());
    }

    @Override
    public int hashCode() {
        return getQuestionId().hashCode();
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

