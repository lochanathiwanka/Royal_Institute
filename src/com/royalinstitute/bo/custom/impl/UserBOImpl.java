package com.royalinstitute.bo.custom.impl;

import com.royalinstitute.bo.custom.UserBO;
import com.royalinstitute.dao.DAOFactory;
import com.royalinstitute.dao.custome.UserDAO;
import com.royalinstitute.dto.UserDTO;
import com.royalinstitute.entity.User;
import com.royalinstitute.util.FactoryConfiguration;
import org.hibernate.Session;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public UserDTO getUserDetails(String userName, String pwd) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        session.getTransaction().begin();

        UserDTO userDTO = null;
        try {
            User user = userDAO.getUserDetails(userName, pwd);
            userDTO = new UserDTO(user.getUid(), user.getName(), user.getUserName(), user.getPassword());
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }

        return userDTO;
    }

    @Override
    public void update(UserDTO userDTO) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        session.getTransaction().begin();

        try {
            userDAO.update(new User(userDTO.getId(), userDTO.getName(), userDTO.getUserName(), userDTO.getPassword()));
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
    }

    @Override
    public void addUser(UserDTO userDTO) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        userDAO.setSession(session);
        session.getTransaction().begin();

        try {
            userDAO.add(new User(userDTO.getId(), userDTO.getName(), userDTO.getUserName(), userDTO.getPassword()));
            session.getTransaction().commit();
        } catch (Throwable t) {
            session.getTransaction().rollback();
            throw t;
        } finally {
            session.close();
        }
    }
}
