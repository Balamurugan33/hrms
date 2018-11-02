package com.ideas2it.hrms.dao;

import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Employee;

/**
 * Used to perform the CRUD operation on database 
 * for employee details 
 * 
 * @version 1
 * @author Balamurugan M
 */
public interface EmployeeDao {
    
    /**
     * Creates a new employee 
     * 
     * @param employee
     *        Used get the employee details
     */
    Boolean createEmployee(Employee employee) throws AppException;
    
    /**
     * Update an existing employee 
     * 
     * @param employee
     *        Used get the employee details
     */
    Boolean updateEmployee(Employee employee) throws AppException;
    
    /** 
     * Get the particular employee
     * @param email
     *        Used to get the employee email id
     */
    Employee searchEmployee(String emailId) throws AppException;
    
    /**
     * Remove an existing employee 
     * 
     * @param id
     *        employee id
     */
    Boolean deleteEmployee(Integer id) throws AppException;
    
    /**
     * Get all the existing employee 
     */
    List<Employee> retrieveEmployees() throws AppException;
    
}