package com.ideas2it.hrms.service;

import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Employee;

/**
 * It's act as the intermediate between the controller and dao
 * And it's used to perform  all business logic   
 * 
 * @version 1
 * @author Balamurugan M
 */
public interface EmployeeService {
    
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
    * Get all the existing employees
    */
   List<Employee> displayEmployees() throws AppException;
   
   /**
    * Remove an existing employee 
    * 
    * @param id
    *        Used to get the employee id
    */
   Boolean deleteEmployee(Integer id) throws AppException;
   
   /**
    * Used check the employee is exist or not
    * @param email
    *        Get the employee mail id
    */
   Boolean isEmployeeExist(String email) throws AppException;
}