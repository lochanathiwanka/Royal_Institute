package com.royalinstitute.bo.custom;

import com.royalinstitute.bo.SuperBO;
import com.royalinstitute.dto.ProgramDTO;
import com.royalinstitute.dto.RegistrationDTO;
import com.royalinstitute.dto.StudentDTO;

import java.util.List;

public interface RegistrationBO extends SuperBO {
    void makeRegistration(StudentDTO student, RegistrationDTO registrationDTO) throws Exception;

    String getLastId() throws Exception;
}
