package com.royalinstitute.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Registration implements SuperEntity {
    @Id
    private String regId;
    @ManyToOne()
    private Student student;
    private String date;
    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL)
    private List<RegistrationDetail> registrationDetailList;

    public Registration() {
    }

    public Registration(String regId, String date) {
        this.regId = regId;
        this.date = date;
    }

    public Registration(String regId, String date, Student student) {
        this.regId = regId;
        this.date = date;
        this.student = student;
    }

    public Registration(String regId, Student student, String date, List<RegistrationDetail> registrationDetailList) {
        this.regId = regId;
        this.student = student;
        this.date = date;
        this.registrationDetailList = registrationDetailList;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<RegistrationDetail> getRegistrationDetailList() {
        return registrationDetailList;
    }

    public void setRegistrationDetailList(List<RegistrationDetail> registrationDetailList) {
        this.registrationDetailList = registrationDetailList;
    }
}
