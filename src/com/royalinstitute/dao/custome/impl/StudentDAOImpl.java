package com.royalinstitute.dao.custome.impl;

import com.royalinstitute.dao.CrudDAOImpl;
import com.royalinstitute.dao.custome.StudentDAO;
import com.royalinstitute.entity.Student;
import com.royalinstitute.util.FactoryConfiguration;
import org.hibernate.query.NativeQuery;

public class StudentDAOImpl extends CrudDAOImpl <Student, String> implements StudentDAO {
    @Override
    public String getLastId() throws Exception {
        NativeQuery sqlQuery = session.createSQLQuery("SELECT sid FROM Student ORDER BY sid DESC LIMIT 1");
        return sqlQuery.uniqueResult().toString();
    }
}
