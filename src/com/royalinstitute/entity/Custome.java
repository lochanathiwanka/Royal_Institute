package com.royalinstitute.entity;

public class Custome implements SuperEntity {
    private String sid;
    private String name;
    private String address;

    private String regId;
    private String date;
    private String program;

    public Custome() {
    }

    public Custome(String sid, String regId, String name, String address) {
        this.sid = sid;
        this.regId = regId;
        this.name = name;
        this.address = address;
    }

    public Custome(String regId, String date, String program) {
        this.regId = regId;
        this.date = date;
        this.program = program;
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

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
