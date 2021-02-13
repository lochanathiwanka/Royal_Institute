package com.royalinstitute.dto;

import java.util.List;

public class RegistrationDTO {
    private String regId;
    private String sid;
    private String date;
    private double fee;
    private List<RegistrationDetailDTO> registrationDetailDTOList;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String regId, String sid, String date, List<RegistrationDetailDTO> registrationDetailDTOList) {
        this.regId = regId;
        this.sid = sid;
        this.date = date;
        this.registrationDetailDTOList = registrationDetailDTOList;
    }

    public RegistrationDTO(String regId, String sid, String date, double fee, List<RegistrationDetailDTO> registrationDetailDTOList) {
        this.regId = regId;
        this.sid = sid;
        this.date = date;
        this.fee = fee;
        this.registrationDetailDTOList = registrationDetailDTOList;
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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public List<RegistrationDetailDTO> getRegistrationDetailDTOList() {
        return registrationDetailDTOList;
    }

    public void setRegistrationDetailDTOList(List<RegistrationDetailDTO> registrationDetailDTOList) {
        this.registrationDetailDTOList = registrationDetailDTOList;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
