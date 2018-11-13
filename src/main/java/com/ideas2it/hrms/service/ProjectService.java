package com.ideas2it.hrms.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Client;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.model.TimeSheet;

/**
 * <p>
 * Provides an interface for basic CRUD operations on the Project Model:
 * Add new project, Get project, Get all projects,
 * Update Project, Remove project
 * </p>
 *
 * @author Ganesh Venkat S
 */
public interface ProjectService {
    
    /**
     * Creates a new project 
     * 
     * @param project
     *       new project
     * @return 
     *       new project, if added, null otherwise
     */
    Project createProject(Project project) throws AppException;
    
    /**
     * Gets a project 
     *
     * @param id
     *        id of the requested project
     * @return
     *        requested project, if exists, null otherwise
     */
    Project getProjectById(Integer id) throws AppException;
    
    /**
     * Gets all the projects allocated to company
     * 
     * @return
     *        list of all projects
     */
    List<Project> getAllProjects() throws AppException;
    
    /**
     * Updates a project's details
     * 
     * @param project
     *        project to update
     */
    Project updateProject(Project project) throws AppException;
    
    /**
     * Removes a project
     * 
     * @param project
     *        project to remove
     */
    Project removeProject(Project project) throws AppException;
    
    /**
     * Calculates the net profit of the company, for current month,
     * from a single project
     * @param employee
     *        company project
     */
    Integer calculateNetProfit(Project project, LocalDate startDate, LocalDate endDate);
    
    /**
     * Calculates the total billable amount for a project, for current month
     * 
     * @param project
     *        billable project 
     */
    Integer calculateBillableAmount(Project project, LocalDate startDate, LocalDate endDate);
    
    /**
     * Calculates the total bill for tasks, for a project, for current month
     * 
     * @param curMonthTasks
     *        list of all tasks for this project, in current month
     */
    Integer calculateBillAllTasks(List<TimeSheet> curMonthTasks);
    
    /**
     * Calculates the total amount paid to employees, working on a project, for current month
     * 
     * @param project
     *        project
     */
    Integer calculateCostToCompany(Project project, LocalDate startDate, LocalDate endDate);
    
    /**
     * Used to get all clients
     */
    List<Client> displayClients() throws AppException;
}