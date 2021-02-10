package com.royalinstitute.dao.custome.impl;

import com.royalinstitute.dao.CrudDAOImpl;
import com.royalinstitute.dao.custome.StudentDAO;
import com.royalinstitute.entity.Student;
import com.royalinstitute.util.FactoryConfiguration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl extends CrudDAOImpl <Student, String> implements StudentDAO {
    @Override
    public String getLastId() throws Exception {
        NativeQuery sqlQuery = session.createSQLQuery("SELECT sid FROM Student ORDER BY sid DESC LIMIT 1");
        return sqlQuery.uniqueResult().toString();
    }

    @Override
    public List<Student> findProgram(String value) throws Exception {
        Query query = session.createQuery("FROM Student WHERE sid LIKE ?1 OR name LIKE ?2");
        query.setParameter(1, "%"+value+"%");
        query.setParameter(2, "%"+value+"%");
        return query.list();
    }
}
