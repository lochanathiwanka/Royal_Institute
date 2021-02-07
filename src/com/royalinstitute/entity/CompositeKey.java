package com.royalinstitute.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompositeKey implements Serializable {
    private String regId;
    private String pid;

    public CompositeKey() {
    }

    public CompositeKey(String regId, String pid) {
        this.regId = regId;
        this.pid = pid;
    }

    public String getRegId() {
        return regId;
    }

    public String getPid() {
        return pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositeKey)) return false;
        CompositeKey that = (CompositeKey) o;
        return Objects.equals(getRegId(), that.getRegId()) &&
                Objects.equals(getPid(), that.getPid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRegId(), getPid());
    }
}
