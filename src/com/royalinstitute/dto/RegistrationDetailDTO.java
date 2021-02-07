package com.royalinstitute.dto;

public class RegistrationDetailDTO {
    private String regId;
    private String pid;

    public RegistrationDetailDTO() {
    }

    public RegistrationDetailDTO(String regId, String pid) {
        this.regId = regId;
        this.pid = pid;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
