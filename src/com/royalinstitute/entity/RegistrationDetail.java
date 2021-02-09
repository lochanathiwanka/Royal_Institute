package com.royalinstitute.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RegistrationDetail implements SuperEntity {
    @EmbeddedId
    private CompositeKey pk;
    @ManyToOne
    @JoinColumn(name = "regId", referencedColumnName = "regId", insertable = false, updatable = false)
    private Registration registration;
    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid", insertable = false, updatable = false)
    private Program program;

    public RegistrationDetail() {
    }

    public RegistrationDetail(CompositeKey pk) {
        this.pk = pk;
    }

    public RegistrationDetail(CompositeKey pk, Registration registration, Program program) {
        this.pk = pk;
        this.registration = registration;
        this.program = program;
    }

    public CompositeKey getPk() {
        return pk;
    }

    public void setPk(CompositeKey pk) {
        this.pk = pk;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
}
