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
@Table(name = "usergroup")
@NamedQueries({
        @NamedQuery(name = "com.myapps.infiquest.core.UserGroup.findAll",
                query = "select u from UserGroup u"),
        @NamedQuery(name = "com.myapps.infiquest.core.UserGroup.findByName",
                query = "select u from UserGroup u where u.usergroupName = :name"),


})

public class UserGroup implements Principal
{

    public UserGroup()
    {
    }

    @Id
    @Column(name="USERGROUP_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long usergroupId;

    @Column(name="USERGROUP_NAME")
    private String usergroupName;

    @Column(name="USERGROUP_EMAILIDS")
    private String usergroupEmailIds;

    @Column(name="USERGROUP_IMAGE")
    private String usergroupImage;

    @Column(name = "USERGROUP_CREATION_TIMESTAMP")
    private Long creationTime;

    public UserGroup(Long usergroupId, String usergroupName, String usergroupEmailIds, String usergroupImage, Long creationTime)
    {
        this.usergroupId = usergroupId;
        this.usergroupName = usergroupName;
        this.usergroupEmailIds = usergroupEmailIds;
        this.usergroupImage = usergroupImage;
        this.creationTime = creationTime;
    }

    public Long getUsergroupId() {
        return usergroupId;
    }

    public void setUsergroupId(Long usergroupId) {
        this.usergroupId = usergroupId;
    }

    public String getUsergroupName() {
        return usergroupName;
    }

    public void setUsergroupName(String usergroupName) {
        this.usergroupName = usergroupName;
    }

    public String getUsergroupEmailIds() {
        return usergroupEmailIds;
    }

    public void setUsergroupEmailIds(String usergroupEmailIds) {
        this.usergroupEmailIds = usergroupEmailIds;
    }

    public String getUsergroupImage() {
        return usergroupImage;
    }

    public void setUsergroupImage(String usergroupImage) {
        this.usergroupImage = usergroupImage;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserGroup)) return false;

        UserGroup userGroup = (UserGroup) o;

        return getUsergroupId().equals(userGroup.getUsergroupId());
    }

    @Override
    public int hashCode() {
        return getUsergroupId().hashCode();
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
