package com.royalinstitute.bo.custom;

import com.jfoenix.controls.JFXPasswordField;
import com.royalinstitute.bo.SuperBO;
import com.royalinstitute.dto.UserDTO;

public interface UserBO extends SuperBO {
    UserDTO getUserDetails(String userName, String pwd) throws Exception;

    void update(UserDTO userDTO) throws Exception;
}
