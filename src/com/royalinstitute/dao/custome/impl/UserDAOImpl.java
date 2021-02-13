package com.royalinstitute.dao.custome.impl;

import com.royalinstitute.dao.CrudDAOImpl;
import com.royalinstitute.dao.custome.UserDAO;
import com.royalinstitute.entity.User;
import org.hibernate.query.Query;

public class UserDAOImpl extends CrudDAOImpl<User, String> implements UserDAO {
    @Override
    public User getUserDetails(String userName, String pwd) throws Exception {
        Query query = session.createQuery("FROM User WHERE (userName = ?1 AND password = ?2)");
        query.setParameter(1, userName);
        query.setParameter(2, pwd);
        return (User) query.uniqueResult();
    }
}
