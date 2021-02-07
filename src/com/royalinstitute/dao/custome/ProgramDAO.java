package com.royalinstitute.dao.custome;

import com.royalinstitute.dao.CrudDAO;
import com.royalinstitute.entity.Program;

public interface ProgramDAO extends CrudDAO <Program, String> {
    String getLastId() throws Exception;
    Program findProgram(String value) throws Exception;
}
