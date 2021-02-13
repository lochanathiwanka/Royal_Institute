package com.royalinstitute.bo.custom.impl;

import com.royalinstitute.bo.custom.RegistrationDetailBO;
import com.royalinstitute.dao.DAOFactory;
import com.royalinstitute.dao.custome.QueryDAO;
import com.royalinstitute.dto.CustomeDTO;
import com.royalinstitute.entity.Custome;
import com.royalinstitute.util.FactoryConfiguration;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class RegistrationDetailBOImpl implements RegistrationDetailBO {
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public List<CustomeDTO> getRegDetailsBySid(String sid) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        queryDAO.setSession(session);
        session.getTransaction().begin();

        List<CustomeDTO> list = new ArrayList<>();
        try {
            List<Custome> all = queryDAO.getRegDetailsBySid(sid);
            for (Custome c : all) {
                list.add(new CustomeDTO(c.getRegId(),c.getDate(), c.getProgram()));
            }
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<CustomeDTO> getRegDetailsByPid(String pid) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        queryDAO.setSession(session);
        session.getTransaction().begin();

        List<CustomeDTO> list = new ArrayList<>();
        try {
            List<Custome> all = queryDAO.getRegDetailsByPid(pid);
            for (Custome c : all) {
                list.add(new CustomeDTO(c.getSid(),c.getRegId(), c.getName(), c.getAddress()));
            }
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
        return list;
    }
}
