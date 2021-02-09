package com.royalinstitute.dao.custome;

import com.royalinstitute.dao.CrudDAO;
import com.royalinstitute.entity.Registration;

public interface RegistrationDAO extends CrudDAO <Registration, String> {
    String getLastId() throws Exception;
}
