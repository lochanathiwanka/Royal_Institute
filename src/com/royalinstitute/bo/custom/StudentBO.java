package com.royalinstitute.bo.custom;

import com.royalinstitute.bo.SuperBO;
import com.royalinstitute.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    String getLastId() throws Exception;

    List<StudentDTO> getAll() throws Exception;

    StudentDTO search(String id) throws Exception;

    void update(StudentDTO studentDTO) throws Exception;

    void delete(String id) throws Exception;

    List<StudentDTO> findStudents(String value) throws Exception;
}
