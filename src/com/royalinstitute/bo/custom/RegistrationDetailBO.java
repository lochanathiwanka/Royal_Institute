package com.royalinstitute.bo.custom;

import com.royalinstitute.bo.SuperBO;
import com.royalinstitute.dto.CustomeDTO;

import java.util.List;

public interface RegistrationDetailBO extends SuperBO {
    List<CustomeDTO> getRegDetailsBySid(String sid) throws Exception;

    List<CustomeDTO> getRegDetailsByPid(String pid) throws Exception;
}
