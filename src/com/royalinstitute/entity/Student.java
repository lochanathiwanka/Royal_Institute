package com.royalinstitute.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Student implements SuperEntity {
    @Id
    private String sid;
    private String name;
    private String address;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Registration> registrationList;

    public Student() {
    }

    public Student(String sid, String name, String address) {
        this.sid = sid;
        this.name = name;
        this.address = address;
    }

    public Student(String sid, String name, String address, List<Registration> registrationList) {
        this.sid = sid;
        this.name = name;
        this.address = address;
        this.registrationList = registrationList;
    }

    public String getSid() {
        return sid;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Registration> getRegistrationList() {
        return registrationList;
    }
}
