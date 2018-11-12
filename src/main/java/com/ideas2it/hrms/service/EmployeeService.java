package com.ideas2it.hrms.service;

import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Attendance;
import com.ideas2it.hrms.model.Designation;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.model.ProjectTask;

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
   
   List<Attendance> markPresent(Employee employee) throws AppException;
   
   List<Attendance> markAbsent(Employee employee) throws AppException;
   
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
   List<Project> getEmpProjects(List<ProjectTask> tasks);
      
   /**
    * Calculates the net profit of the company, for current month,
    * from a single employee
    * @param employee
    *        company employee
    */
   Integer calculateNetProfit(Employee employee);
   
   /**
    * Calculates the total billable amount, for current month,
    * from a single employee
    * @param employee
    *        company employee
    */
   Integer calculateBillableAmount(Employee employee);
   
   /**
    * Calculates the total hours worked on tasks, for current month
    * 
    * @param curMonthTasks
    *        tasks done this month by an employee
    */
   Integer calculateHoursWorkedEmp(List<ProjectTask> curMonthTasks);

   /**
    * Calculates the total amount paid to employee by company, for current month
    * 
    * @param employee
    *        company employee
    */
   Integer calculateCostToCompany(Employee employee);

   /**
    * Gets the num of leaves taken by employee, for current month
    * 
    * @param employee
    *        company employee
    */
   Integer getNumLeaves(Employee employee);
   
   /**
    * Returns true if employee has taken leave on this day, otherwise false
    * 
    * @param attendance
    *        attendance of a day
    */
   boolean isEmpLeave(Attendance attendance);
   
   /**
    * Used to gets the all projects
    */
   List<Project> getAllProjects() throws AppException;
   
   /**
    * Used to create the task
    */
   boolean createTask(ProjectTask task) throws AppException;
}