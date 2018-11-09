package com.ideas2it.hrms.service.impl;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.dao.UserDao;
import com.ideas2it.hrms.dao.impl.UserDaoImpl;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.model.User;
import com.ideas2it.hrms.service.EmployeeService;
import com.ideas2it.hrms.service.impl.EmployeeServiceImpl;
import com.ideas2it.hrms.service.UserService;

/**
 * <p>
 * The {@code UserServiceImpl} is represents the collection of 
 * the user service functions
 * This act the intermediate between the controller class and Dao
 * </p>
 *
 * @version 1
 * @author Balamurugan M
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
    
    /** {@inheritDoc}*/
    public Boolean createUser(User user) throws AppException {
        return userDao.createUser(user);
    }
    
    /** {@inheritDoc}*/
    public User searchUser(String userName, String password) 
            throws AppException {
        return userDao.searchUser(userName, password);
    }
    
    /** {@inheritDoc}*/
    public User searchName(String userName) throws AppException {
        return userDao.searchName(userName);
    }
    
    /** {@inheritDoc}*/
    public Employee checkEmployeeDetail(String mailId) throws AppException {
        EmployeeService employeeService = new EmployeeServiceImpl();
        return employeeService.searchEmployee(mailId);
    }
}