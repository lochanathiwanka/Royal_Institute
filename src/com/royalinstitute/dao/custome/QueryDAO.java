package com.royalinstitute.dao.custome;

import com.royalinstitute.dao.SuperDAO;
import com.royalinstitute.entity.Custome;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Custome> getRegDetailsBySid(String sid) throws Exception;

    List<Custome> getRegDetailsByPid(String pid) throws Exception;
}
