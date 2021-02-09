package com.royalinstitute.bo.custom.impl;

import com.royalinstitute.bo.custom.StudentBO;
import com.royalinstitute.dao.DAOFactory;
import com.royalinstitute.dao.custome.ProgramDAO;
import com.royalinstitute.dao.custome.StudentDAO;
import com.royalinstitute.dto.StudentDTO;
import com.royalinstitute.entity.Student;
import com.royalinstitute.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {
    StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public String getLastId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        session.getTransaction().begin();

        String lastId = "";
        try {
            lastId = studentDAO.getLastId();
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return lastId;
    }

    @Override
    public List<StudentDTO> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        session.getTransaction().begin();

        List<StudentDTO> list = new ArrayList<>();
        try {
            List<Student> all = studentDAO.getAll();
            for (Student s : all) {
                list.add(new StudentDTO(s.getSid(), s.getName(), s.getAddress()));
            }
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public StudentDTO search(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        studentDAO.setSession(session);
        session.getTransaction().begin();

        StudentDTO studentDTO = null;
        try {
            Student s = studentDAO.search(id);
            studentDTO = new StudentDTO(s.getSid(), s.getName(),s.getAddress());
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return studentDTO;
    }
}
