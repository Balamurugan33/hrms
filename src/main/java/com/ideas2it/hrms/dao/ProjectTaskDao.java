package com.ideas2it.hrms.dao;

import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.ProjectTask;

/**
 * <p>
 * Provides an interface for basic CRUD operations on the ProjectTask Model:
 * Add new task, Get task, Get all tasks,
 * Update task, Remove task
 * </p>
 *
 * @author Ganesh Venkat S
 */
public interface ProjectTaskDao {
    
    /**
     * Creates a new task 
     * 
     * @param task
     *       new task
     * @return 
     *       new task, if added, null otherwise
     */
    ProjectTask createTask(ProjectTask task) throws AppException; 
    
    /**
     * Gets a task
     *
     * @param id
     *        id of the requested task
     * @return
     *        requested task, if exists, null otherwise
     */
    ProjectTask getTaskById(Integer id) throws AppException;
     
    /**
     * Gets all the tasks allocated to company
     * 
     * @return
     *        list of all tasks
     */
    List<ProjectTask> getAllTasks() throws AppException;
    
    /**
     * Updates a task's details
     * 
     * @param task
     *       task to update
     */
    ProjectTask updateTask(ProjectTask task) throws AppException;
    
    /**
     * Removes a task
     * 
     * @param task
     *       task to remove
     */
    ProjectTask removeTask(ProjectTask task) throws AppException;
}