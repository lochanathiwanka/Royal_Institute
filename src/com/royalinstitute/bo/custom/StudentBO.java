package com.royalinstitute.bo.custom;

import com.royalinstitute.bo.SuperBO;
import com.royalinstitute.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    String getLastId() throws Exception;

    List<StudentDTO> getAll() throws Exception;

    StudentDTO search(String id);
}
