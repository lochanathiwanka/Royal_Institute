package com.royalinstitute.dao.custome.impl;

import com.royalinstitute.dao.CrudDAOImpl;
import com.royalinstitute.dao.custome.ProgramDAO;
import com.royalinstitute.entity.Program;
import com.royalinstitute.util.FactoryConfiguration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class ProgramDAOImpl extends CrudDAOImpl <Program, String> implements ProgramDAO {
    @Override
    public String getLastId() throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        NativeQuery sqlQuery = session.createSQLQuery("SELECT pid FROM Program ORDER BY pid DESC LIMIT 1");
        return sqlQuery.uniqueResult().toString();
    }

    @Override
    public Program findProgram(String value) throws Exception {
        session = FactoryConfiguration.getInstance().getSession();
        Query query = session.createQuery("FROM Program WHERE pid LIKE ?1 OR program LIKE ?2");
        query.setParameter(1, "%"+value+"%");
        query.setParameter(2, "%"+value+"%");
        return (Program) query.uniqueResult();
    }
}
