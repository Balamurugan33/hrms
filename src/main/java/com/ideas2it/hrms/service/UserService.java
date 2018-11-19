package com.ideas2it.hrms.service;

import java.time.LocalDate;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.model.User;

/**
 * It's act as the intermediate between the controller and dao
 * And it's used to perform  all business logic   
 * 
 * @version 1
 * @author Balamurugan M
 */
public interface UserService {
    
    /**
     * Used to create the User in the hrms 
     *
     * @param user
     *        Used to get the User reference
     */
    Integer createUser(User user) throws AppException;
    
    /**
     * Used to find the user in the hrms
     *
     * @param userName, password
     *        Used to find the unique user
     */
    User searchUser(String userName, String password) throws AppException;
    
    /**
     * Used to check the user user name is already exist
     *
     * @param userName
     *        Used to find the user name
     */
    User searchName(String userName) throws AppException;
    
    /**
     * Used to check the employee details are exist in the hrms
     * @param id
     *        Used to find the user
     */
    Employee checkEmployeeDetail(String mailId) throws AppException;
    
    /**
     * Used to create the employee login
     * 
     * @param employee
     *        Employee details
     * @return
     */
    boolean createEmpLogin(Employee employee) throws AppException;
            
    Integer getCompanyNetProfit(LocalDate startDate, LocalDate endDate) throws AppException;
    
    Integer getCompanyRevenue(LocalDate startDate, LocalDate endDate) throws AppException;

    Integer getCompanyExpenditure(LocalDate startDate, LocalDate endDate) throws AppException;
}