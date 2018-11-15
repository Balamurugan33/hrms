package com.ideas2it.hrms.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.TimeSheet;

/**
 * <p>
 * Provides an interface for basic CRUD operations on the TimeSheet Model:
 * Add new task, Get task, Get all tasks,
 * Update Task, Remove task
 * </p>
 *
 * @author Ganesh Venkat S
 */
public interface TimeSheetService {
    
    /**
     * Creates a new task 
     * 
     * @param task
     *        new task
     * @return 
     *        new task, if added, null otherwise
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
     *        task to update
     */
    TimeSheet updateTask(TimeSheet task) throws AppException;
    
    /**
     * Removes a task
     * 
     * @param task
     *        task to remove
     */
    TimeSheet removeTask(TimeSheet task) throws AppException;
    
    /**
     * Gets the tasks done this month
     * 
     * @param tasks
     *        list of project tasks
     */
    List<TimeSheet> getTimeSheetEntries(List<TimeSheet> tasks, LocalDate startDate, LocalDate endDate);
    
    /** 
     * Determines whether a timesheet entry was made between a start and end date
     * @param task
     *        timesheet entry
     * @param startDate
     *        starting date
     * @param endDate
     *        ending date
     * @return
     */
    boolean isEntryBetweenPeriod(TimeSheet task, LocalDate startDate, LocalDate endDate);    
}