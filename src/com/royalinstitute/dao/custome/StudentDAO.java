package com.royalinstitute.dao.custome;

import com.royalinstitute.dao.CrudDAO;
import com.royalinstitute.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO <Student, String> {
    String getLastId() throws Exception;

    List<Student> findProgram(String value) throws Exception;
}
