package com.royalinstitute.bo.custom.impl;

import com.royalinstitute.bo.custom.RegistrationBO;
import com.royalinstitute.dao.DAOFactory;
import com.royalinstitute.dao.custome.ProgramDAO;
import com.royalinstitute.dao.custome.RegistrationDAO;
import com.royalinstitute.dao.custome.RegistrationDetailDAO;
import com.royalinstitute.dao.custome.StudentDAO;
import com.royalinstitute.dto.RegistrationDTO;
import com.royalinstitute.dto.RegistrationDetailDTO;
import com.royalinstitute.dto.StudentDTO;
import com.royalinstitute.entity.CompositeKey;
import com.royalinstitute.entity.Registration;
import com.royalinstitute.entity.RegistrationDetail;
import com.royalinstitute.entity.Student;
import com.royalinstitute.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegistrationBOImpl implements RegistrationBO {
    StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    ProgramDAO programDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PROGRAM);
    RegistrationDAO registrationDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.REGISTRATION);
    RegistrationDetailDAO registrationDetailDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.REGISTRATIONDETAIL);

    @Override
    public void makeRegistration(StudentDTO s, RegistrationDTO registrationDTO) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        programDAO.setSession(session);
        registrationDAO.setSession(session);
        registrationDetailDAO.setSession(session);

        session.getTransaction().begin();

        try {
            Student isSearched = studentDAO.search(s.getSid());

            Student student = new Student();
            student.setSid(s.getSid());
            student.setName(s.getName());
            student.setAddress(s.getAddress());

            Registration registration = new Registration();
            registration.setRegId(registrationDTO.getRegId());
            registration.setDate(registrationDTO.getDate());
            registration.setStudent(student);

            List<RegistrationDetail> regDetailList = new ArrayList<>();
            for (RegistrationDetailDTO reg : registrationDTO.getRegistrationDetailDTOList()) {
                CompositeKey pk = new CompositeKey(reg.getRegId(), reg.getPid());
                regDetailList.add(new RegistrationDetail(pk));
            }

            registration.setRegistrationDetailList(regDetailList);
            student.setRegistrationList(Arrays.asList(registration));

            if (isSearched == null) {
                studentDAO.add(student);
            } else {
                registrationDAO.add(registration);
            }

            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    @Override
    public String getLastId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        registrationDAO.setSession(session);
        session.getTransaction().begin();

        String lastId = "";
        try {
            lastId = registrationDAO.getLastId();
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
        return lastId;
    }
}
