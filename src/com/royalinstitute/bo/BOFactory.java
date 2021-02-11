package com.royalinstitute.bo;

import com.royalinstitute.bo.custom.impl.ProgramBOImpl;
import com.royalinstitute.bo.custom.impl.RegistrationBOImpl;
import com.royalinstitute.bo.custom.impl.RegistrationDetailBOImpl;
import com.royalinstitute.bo.custom.impl.StudentBOImpl;
import com.royalinstitute.dao.custome.impl.RegistrationDetailDAOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        STUDENT, PROGRAM, REGISTRATION, REGISTRATIONDETAIL
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case STUDENT:
                return (T) new StudentBOImpl();
            case PROGRAM:
                return (T) new ProgramBOImpl();
            case REGISTRATION:
                return (T) new RegistrationBOImpl();
            case REGISTRATIONDETAIL:
                return (T) new RegistrationDetailBOImpl();
            default:
                return null;
        }
    }
}
