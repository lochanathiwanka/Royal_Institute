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
        Query query = session.createQuery("SELECT r.regId, r.date, p.program FROM Registration r " +
                "INNER JOIN r.student s INNER JOIN r.registrationDetailList rd INNER JOIN rd.program p WHERE s.sid = ?1");
        query.setParameter(1, sid);
        List<Object[]> list = query.list();
        List<Custome> all = new ArrayList<>();

        for (Object[] objects : list) {
            all.add(new Custome(objects[0].toString(), objects[1].toString(), objects[2].toString()));
        }
        return all;
    }

    @Override
    public List<Custome> getRegDetailsByPid(String pid) throws Exception {
        Query query = session.createQuery("SELECT s.sid, r.regId, s.name, s.address FROM Registration r " +
                "INNER JOIN r.student s INNER JOIN r.registrationDetailList rd INNER JOIN rd.program p WHERE p.pid = ?1");
        query.setParameter(1, pid);
        List<Object[]> list = query.list();
        List<Custome> all = new ArrayList<>();

        for (Object[] objects : list) {
            all.add(new Custome(objects[0].toString(), objects[1].toString(), objects[2].toString(), objects[3].toString()));
        }
        return all;
    }
}
