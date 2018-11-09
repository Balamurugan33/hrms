package com.ideas2it.hrms.dao;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.User;

/**
 * Used to perform the CRUD operation on database 
 * for user details 
 * 
 * @version 1
 * @author Balamurugan M
 */
public interface UserDao {
    
    /**
     * Used to create the User in the hrms 
     *
     * @param user
     *        Used to get the User reference
     */
    Boolean createUser(User user) throws AppException;
    
    /**
     * Used to find the user in the hrms
     *
     * @param userName, password, role
     *        Used to find the unique user
     */
    User searchUser(String userName, String password) throws AppException;
    
    /**
     * Used to check the user name is already there 
     *
     * @param userName
     *        Used to find the user name
     */
    User searchName(String userName) throws AppException;
    
}