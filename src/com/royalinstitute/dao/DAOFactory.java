package com.royalinstitute.dao;

import com.royalinstitute.dao.custome.impl.ProgramDAOImpl;
import com.royalinstitute.dao.custome.impl.RegistrationDAOImpl;
import com.royalinstitute.dao.custome.impl.RegistrationDetailDAOImpl;
import com.royalinstitute.dao.custome.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        STUDENT, PROGRAM, REGISTRATION, REGISTRATIONDETAIL
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case STUDENT:
                return (T) new StudentDAOImpl();
            case PROGRAM:
                return (T) new ProgramDAOImpl();
            case REGISTRATION:
                return (T) new RegistrationDAOImpl();
            case REGISTRATIONDETAIL:
                return (T) new RegistrationDetailDAOImpl();
            default:
                return null;
        }
    }
}
