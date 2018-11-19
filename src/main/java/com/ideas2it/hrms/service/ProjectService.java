package com.ideas2it.hrms.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Client;
import com.ideas2it.hrms.model.Project;

/**
 * <p>
 * Provides an interface for basic CRUD operations on the Project Model:
 * Add new project, Get project, Get all projects,
 * Update Project, Remove project
 * </p>
 *
 * <p>
 * Provides additional functionality to:
 * Calculate the company's netProfit from a project, over a time interval
 * Calculate the company's revenue from a project, over a time interval
 * Calculate the company's expenditure, in terms of salary paid to employees for a project, over a time interval
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
     * Gets all the projects allocated to the company
     * 
     * @return
     *        all projects allocated to the company
     */
    List<Project> getAllProjects() throws AppException;
    
    /**
     * Updates a project's info
     * 
     * @param project
     *        a project, with updated info 
     * @return
     *        the updated project
     */
    Project updateProject(Project project) throws AppException;
    
    /**
     * Removes a project
     * 
     * @param project
     *        a project
     * @return
     *        the project that was removed
     */
    Project removeProject(Project project) throws AppException;
    
    /**
     * Calculates the company's netProfit from a project, over a time interval
     * 
     * @param project
     *        a project
     * @param startDate
     *        starting date of the interval
     * @param endDate
     *        ending date of the interval
     * @return
     *        company's netProfit from the project, over the given time interval                      
     */
    Integer calculateNetProfit(Project project, LocalDate startDate, LocalDate endDate) throws AppException;
    
    /**
     * Calculate the company's revenue from a project, over a time interval
     * 
     * @param project
     *        a project 
     * @param startDate
     *        starting date of the interval
     * @param endDate
     *        ending date of the interval
     * @return
     *        company's revenue from the project, over the given time interval       
     */
    Integer calculateBillableAmount(Project project, LocalDate startDate, LocalDate endDate);
        
    /**
     * Calculate the company's expenditure, in terms of salary paid to employees for a project, over a time interval
     * 
     * @param project
     *        a project 
     * @param startDate
     *        starting date of the interval
     * @param endDate
     *        ending date of the interval
     * @return
     *        company's expenditure for the project, over the given time interval       
     */
    Integer calculateCostToCompany(Project project, LocalDate startDate, LocalDate endDate) throws AppException;
    
    /**
     * Gets all the clients of the company
     * 
     * @return
     *        all clients of the company
     */
    List<Client> getAllClients() throws AppException;
}