package com.ideas2it.hrms.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Attendance;
import com.ideas2it.hrms.model.Designation;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.model.SalaryTracker;
import com.ideas2it.hrms.model.TimeSheet;

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
    * Used by an employee to send his project manager an email, applying for leave
    * @param employee
    *        an employee
    * @param message
    *        reason for leave
    * @param dateString
    *        date of leave
    * @return
    *        the employee's attendance history    
    */
   List<Attendance> applyLeave(Employee employee, String message, String dateString) throws AppException;

   /**
    * Marks an employee as present, for current date, in his/her attendance history
    * @param employee
    *        an employee
    * @return
    *        the employee's attendance history    
    */
   List<Attendance> markPresent(Employee employee) throws AppException;
   
   /**
    * Marks an employee as absent, for current date, in his/her attendance history
    * @param employee
    *        an employee
    * @return
    *        the employee's attendance history    
    */
   List<Attendance> markAbsent(Employee employee) throws AppException;
   
   /**
    * Gets the entire attendance history of an employee
    * 
    * @param employee
    *        an employee
    * @return
    *        the employee's attendance history    
    */
   List<Attendance> getAttendanceSheet(Employee employee) throws AppException;
   
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
    * @param emailId
    *        Get the employee mail id
    */
   Boolean isEmployeeExist(String emailId) throws AppException;
   
   /**
    * Used get the existing employee
    * @param emailId
    *        Get the employee mail id
    */
   Employee searchEmployee(String emailId) throws AppException;
   
   /**
    * Used get the all the designations
    */
   List<Designation> getDesignations() throws AppException;
   
   /**
    * Used get the employee assigned projects
    * @param tasks
    *        Get the employee tasks
    */
   List<Project> getEmpProjects(List<TimeSheet> tasks);
      
   /**
    * Calculates the net profit of the company, between the two different date
    * from a single employee
    * *
    * @param startDate
    *        starting working date
    * @param endDate
    *        ending working date
    * @param employee
    *        company employee
    */
   Integer calculateNetProfit(LocalDate startDate, LocalDate endDate, 
           Employee employee) throws AppException;
   
   /**
    * Calculates the total amount paid to employee by company,
    * between the two different date
    * 
    * @param startDate
    *        starting working date
    * @param endDate
    *        ending working date
    * @param employee
    *        company employee
    */
   Integer calculateCostToCompany(LocalDate startDate, 
           LocalDate endDate, Employee employee) throws AppException;
   
   /**
    * Calculates the total billable amount, between the two different date
    * from a single employee
    * 
    * @param startDate
    *        starting working date
    * @param endDate
    *        ending working date
    * @param employee
    *        company employee
    */
   Integer calculateBillAmount(LocalDate startDate, LocalDate endDate, 
           Employee employee);
   
   /**
    * Used to gets the all projects
    */
   List<Project> getAllProjects() throws AppException;
   
   /**
    * Used to create the task
    */
   boolean createTask(TimeSheet task) throws AppException;
   
   /**
    * Used to update the employee salary and create the salary tracker
    * 
    * @param emailId
    *        employee emailId
    * @param salaryTracker
    *        salary tracking details 
    */
   boolean salaryIncrement(String emailId, SalaryTracker salaryTracker) 
           throws AppException;
   
   /**
    * Used get the existing project
    * 
    * @param id
    *        project id
    */
   Project getProjectById(Integer id) throws AppException;

   /**
    * Get all the existing employees by same name
    */
   List<Employee> searchEmployeeByName(String name) throws AppException;
   
}