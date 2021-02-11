package com.royalinstitute.dao.custome.impl;

import com.royalinstitute.dao.custome.QueryDAO;
import com.royalinstitute.entity.Custome;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    Session session;
    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<Custome> getRegDetailsBySid(String sid) throws Exception {
        NativeQuery sqlQuery = session.createSQLQuery("SELECT rd.regId, date, program FROM Student s, Registration r, RegistrationDetail rd, " +
                "Program p WHERE s.sid = r.sid AND r.regId = rd.regId AND p.pid = rd.pid AND s.sid = ?1");
        sqlQuery.setParameter(1, sid);
        List<Object[]> list = sqlQuery.list();
        List<Custome> all = new ArrayList<>();

        for (Object[] objects : list) {
            all.add(new Custome(objects[0].toString(), objects[1].toString(), objects[2].toString()));
        }
        return all;
    }

    @Override
    public List<Custome> getRegDetailsByPid(String pid) throws Exception {
        NativeQuery sqlQuery = session.createSQLQuery("SELECT s.sid, r.regId, s.name, s.address FROM Student s, Registration r, RegistrationDetail rd, " +
                "Program p WHERE (s.sid = r.sid && r.regId = rd.regId && p.pid = rd.pid) AND p.pid = ?1");
        sqlQuery.setParameter(1, pid);
        List<Object[]> list = sqlQuery.list();
        List<Custome> all = new ArrayList<>();

        for (Object[] objects : list) {
            all.add(new Custome(objects[0].toString(), objects[1].toString(), objects[2].toString(), objects[3].toString()));
        }
        return all;
    }
}
