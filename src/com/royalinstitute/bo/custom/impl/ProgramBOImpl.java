package com.royalinstitute.bo.custom.impl;

import com.royalinstitute.bo.custom.ProgramBO;
import com.royalinstitute.dao.DAOFactory;
import com.royalinstitute.dao.custome.ProgramDAO;
import com.royalinstitute.dto.ProgramDTO;
import com.royalinstitute.entity.Program;
import com.royalinstitute.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ProgramBOImpl implements ProgramBO {
    ProgramDAO programDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PROGRAM);

    @Override
    public List<ProgramDTO> getAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        programDAO.setSession(session);
        session.getTransaction().begin();

        List<ProgramDTO> list = new ArrayList<>();
        try {
            List<Program> all = programDAO.getAll();
            for (Program p : all) {
                list.add(new ProgramDTO(p.getPid(), p.getProgram(), p.getDuration(), p.getFee()));
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
    public void add(ProgramDTO program) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        programDAO.setSession(session);
        session.getTransaction().begin();

        try {
            programDAO.add(new Program(program.getPid(), program.getProgram(), program.getDuration(), program.getFee()));
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public String getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        programDAO.setSession(session);
        session.getTransaction().begin();

        String lastId = "";
        try {
            lastId = programDAO.getLastId();
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return lastId;
    }

    @Override
    public void update(ProgramDTO program) {
        Session session = FactoryConfiguration.getInstance().getSession();
        programDAO.setSession(session);
        session.getTransaction().begin();

        try {
            programDAO.update(new Program(program.getPid(), program.getProgram(), program.getDuration(), program.getFee()));
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        programDAO.setSession(session);
        session.getTransaction().begin();

        try {
            programDAO.delete(id);
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public ProgramDTO findProgram(String value) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        programDAO.setSession(session);
        session.getTransaction().begin();

        ProgramDTO program = null;
        try {
            Program p = programDAO.findProgram(value);
            program = new ProgramDTO(p.getPid(), p.getProgram(), p.getDuration(), p.getFee());
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return program;
    }
}
