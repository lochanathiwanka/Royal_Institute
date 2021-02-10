package com.royalinstitute.dao.custome;

import com.royalinstitute.dao.CrudDAO;
import com.royalinstitute.entity.Program;

import java.util.List;

public interface ProgramDAO extends CrudDAO <Program, String> {
    String getLastId() throws Exception;
    List<Program> findProgram(String value) throws Exception;
}
