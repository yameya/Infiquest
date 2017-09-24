package com.myapps.infiquest.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.security.auth.Subject;
import java.security.Principal;

/**
 * Created by AYellapurkar on 28-11-2016.
 */

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "com.myapps.infiquest.core.Users.findAll",
                query = "select u from Users u"),
        @NamedQuery(name = "com.myapps.infiquest.core.Users.findByName",
                query = "select u from Users u where u.userName = :name"),
        @NamedQuery(name = "com.myapps.infiquest.core.Users.findByNameAndPwd",
                query = "select u from Users u where u.userName = :name and u.userPassword = :password"),
        @NamedQuery(name = "com.myapps.infiquest.core.Users.findByUserId",
                query = "select u from Users u where u.userId = :userId")



})

public class Users implements Principal {
    public Users() {
    }

    @Id
    @Column(name="USER_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long userId;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="USER_EMAILID")
    private String userEmailId;

    @Column(name="USER_PASSWORD")
    private String userPassword;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmailId() {
        return userEmailId;
    }

    public void setUserEmailId(String userEmailId) {
        this.userEmailId = userEmailId;
    }

    public String getUserDisplayPic() {
        return userDisplayPic;
    }

    public void setUserDisplayPic(String userDisplayPic) {
        this.userDisplayPic = userDisplayPic;
    }

    @Column(name="USER_DISPLAYPIC")
    private String userDisplayPic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;

        Users users = (Users) o;

        return getUserId().equals(users.getUserId());

    }

    @Override
    public int hashCode() {
        return getUserId().hashCode();
    }

    @Override
    @Transient
    public String getName()
    {
        return null;
    }

    @Override
    public boolean implies(Subject subject)
    {
        return true;
    }


    public Users(Long userId, String userName, String userEmailId, String userPassword, String userDisplayPic) {
        this.userId = userId;
        this.userName = userName;
        this.userEmailId = userEmailId;
        this.userPassword = userPassword;
        this.userDisplayPic = userDisplayPic;
    }
}
