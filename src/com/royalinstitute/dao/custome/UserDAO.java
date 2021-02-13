package com.royalinstitute.dao.custome;

import com.royalinstitute.dao.CrudDAO;
import com.royalinstitute.entity.User;

public interface UserDAO extends CrudDAO<User, String> {
    User getUserDetails(String userName, String pwd) throws Exception;
}
