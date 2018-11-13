package com.ideas2it.hrms.dao;

import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.TimeSheet;

/**
 * <p>
 * Provides an interface for basic CRUD operations on the TimeSheet Model:
 * Add new task, Get task, Get all tasks,
 * Update task, Remove task
 * </p>
 *
 * @author Ganesh Venkat S
 */
public interface TimeSheetDao {
    
    /**
     * Creates a new task 
     * 
     * @param task
     *       new task
     * @return 
     *       new task, if added, null otherwise
     */
    TimeSheet createTask(TimeSheet task) throws AppException; 
    
    /**
     * Gets a task
     *
     * @param id
     *        id of the requested task
     * @return
     *        requested task, if exists, null otherwise
     */
    TimeSheet getTaskById(Integer id) throws AppException;
     
    /**
     * Gets all the tasks allocated to company
     * 
     * @return
     *        list of all tasks
     */
    List<TimeSheet> getAllTasks() throws AppException;
    
    /**
     * Updates a task's details
     * 
     * @param task
     *       task to update
     */
    TimeSheet updateTask(TimeSheet task) throws AppException;
    
    /**
     * Removes a task
     * 
     * @param task
     *       task to remove
     */
    TimeSheet removeTask(TimeSheet task) throws AppException;
}