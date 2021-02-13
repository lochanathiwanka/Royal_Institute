package com.royalinstitute.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements SuperEntity {
    @Id
    private String uid;
    private String name;
    private String userName;
    private String password;

    public User() {
    }

    public User(String uid, String name, String userName, String password) {
        this.uid = uid;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
