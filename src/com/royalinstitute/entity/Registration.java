package com.royalinstitute.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Registration implements SuperEntity {
    @Id
    private String regId;
    @ManyToOne()
    @JoinColumn(name = "sid")
    private Student student;
    private String date;
    private double fee;
    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL)
    private List<RegistrationDetail> registrationDetailList;

    public Registration() {
    }

    public Registration(String regId, String date, double fee) {
        this.regId = regId;
        this.date = date;
        this.fee = fee;
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

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
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
