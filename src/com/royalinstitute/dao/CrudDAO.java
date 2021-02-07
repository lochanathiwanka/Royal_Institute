package com.royalinstitute.dao;

import com.royalinstitute.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public interface CrudDAO <Entity extends SuperEntity, ID extends Serializable> extends SuperDAO {
    void add(Entity entity) throws Exception;
    void delete(ID id) throws Exception;
    void update(Entity entity) throws Exception;
    Entity search(ID id) throws Exception;
    List<Entity> getAll() throws Exception;
}
