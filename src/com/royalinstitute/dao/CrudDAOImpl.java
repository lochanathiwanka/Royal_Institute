package com.royalinstitute.dao;

import com.royalinstitute.entity.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class CrudDAOImpl <T extends SuperEntity, ID extends Serializable> implements CrudDAO<T, ID> {
    protected Session session;
    private Class <T> entity;

    public CrudDAOImpl() {
        entity = (Class<T>)(((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
    }

    @Override
    public void add(T entity) throws Exception {
        session.save(entity);
    }

    @Override
    public void delete(ID id) throws Exception {
        session.delete(session.load(entity, id));
    }

    @Override
    public void update(T entity) throws Exception {
        session.update(entity);
    }

    @Override
    public T search(ID id) throws Exception {
        return session.get(entity, id);
    }

    @Override
    public List<T> getAll() throws Exception {
        return session.createQuery("FROM "+entity.getName()).list();
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
