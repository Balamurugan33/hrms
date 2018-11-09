package com.ideas2it.hrms.service;

import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.ProjectTask;

/**
 * <p>
 * Provides an interface for basic CRUD operations on the ProjectTask Model:
 * Add new task, Get task, Get all tasks,
 * Update Task, Remove task
 * </p>
 *
 * @author Ganesh Venkat S
 */
public interface ProjectTaskService {
    
    /**
     * Creates a new task 
     * 
     * @param task
     *        new task
     * @return 
     *        new task, if added, null otherwise
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
     *        task to update
     */
    ProjectTask updateTask(ProjectTask task) throws AppException;
    
    /**
     * Removes a task
     * 
     * @param task
     *        task to remove
     */
    ProjectTask removeTask(ProjectTask task) throws AppException;
    
    /**
     * Gets the tasks done this month
     * 
     * @param tasks
     *        list of project tasks
     */
    List<ProjectTask> getCurrentMonthTasks(List<ProjectTask> tasks);
    
    /**
     * Returns true if task was done in current month, otherwise false
     * 
     * @param task
     *        project task
     */
    boolean isCurrentMonthTask(ProjectTask task);
    
    /**
     * Calculates the number of hours worked on a task
     * 
     * @param task
     *        project task
     */
    public Integer calculateTaskDuration(ProjectTask task);
}