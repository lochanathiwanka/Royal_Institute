package com.royalinstitute.dao.custome.impl;

import com.royalinstitute.dao.CrudDAOImpl;
import com.royalinstitute.dao.custome.RegistrationDAO;
import com.royalinstitute.entity.Registration;
import org.hibernate.query.NativeQuery;

public class RegistrationDAOImpl extends CrudDAOImpl <Registration, String> implements RegistrationDAO {
    @Override
    public String getLastId() throws Exception {
        NativeQuery sqlQuery = session.createSQLQuery("SELECT regId FROM Registration ORDER BY regId DESC LIMIT 1");
        return sqlQuery.uniqueResult().toString();
    }
}
