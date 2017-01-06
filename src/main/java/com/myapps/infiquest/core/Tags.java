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
@Table(name = "tags")
@NamedQueries({
        @NamedQuery(name = "com.myapps.infiquest.core.Tags.findAll",
                query = "select t from Tags t"),
        @NamedQuery(name = "com.myapps.infiquest.core.Tags.findByName",
                query = "select t from Tags t where t.tagName = :name"),


})

public class Tags implements Principal
{

    public Tags()
    {
    }

    @Id
    @Column(name="TAG_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long tagId;

    @Column(name="TAG_NAME")
    private String tagName;

    @Column(name="TAG_IMAGE")
    private String tagImage;

    @Column(name = "TAG_TIMESTAMP")
    private Long tagCreationTime;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagImage() {
        return tagImage;
    }

    public void setTagImage(String tagImage) {
        this.tagImage = tagImage;
    }

    public Long getTagCreationTime() {
        return tagCreationTime;
    }

    public void setTagCreationTime(Long tagCreationTime) {
        this.tagCreationTime = tagCreationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tags)) return false;

        Tags tags = (Tags) o;

        return getTagId().equals(tags.getTagId());
    }

    @Override
    public int hashCode() {
        return getTagId().hashCode();
    }

    @Override
    @Transient
    public String getName() {
        return null;
    }

}
