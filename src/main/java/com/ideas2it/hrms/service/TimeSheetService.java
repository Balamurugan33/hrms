package com.ideas2it.hrms.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.TimeSheet;

/**
 * <p>
 * Provides an interface for basic CRUD operations on the TimeSheet Model:
 * Add new entry, Get entry, Get all entries,
 * Update entry, Remove entry
 * </p>
 * 
 * <p>
 * Provides additional functionality to:
 * Find the timesheet entries within a time interval (between 2 dates) 
 *  </p>
 *
 * @author Ganesh Venkat S
 */
public interface TimeSheetService {
    
    /**
     * Creates a new entry
     * 
     * @param entry
     *        new entry
     * @return 
     *        new entry, if added, null otherwise
     */
    TimeSheet createEntry(TimeSheet entry) throws AppException;
    
    /**
     * Gets an entry
     *
     * @param id
     *        id of the requested entry
     * @return
     *        requested timesheet entry, if exists, null otherwise
     */
    TimeSheet getEntryById(Integer id) throws AppException;
    
    /**
     * Gets all the timesheets of all employees
     * 
     * @return
     *        all timesheets of all employees
     */
    List<TimeSheet> getAllEntries() throws AppException;
    
    /**
     * Updates an entry
     * 
     * @param entry
     *        a timesheet entry
     */
    TimeSheet updateEntry(TimeSheet entry) throws AppException;
    
    /**
     * Removes an entry
     * 
     * @param entry
     *        a timesheet entry
     */
    TimeSheet removeEntry(TimeSheet entry) throws AppException;
    
    /** 
     * Gets the timesheet entries within a given time interval
     * 
     * @param timeSheet
     *        list of timesheet entries
     * @param startDate
     *        starting date of the time interval
     * @param endDate
     *        ending date of the time interval
     * @return
     *        timesheet entries within the specified time interval
     */
    List<TimeSheet> getEntriesInInterval(List<TimeSheet> timeSheet, LocalDate startDate, LocalDate endDate);
    
    /** 
     * Determines whether a timesheet entry was made between a start and end date
     * 
     * @param entry
     *        timesheet entry
     * @param startDate
     *        starting date of the time interval
     * @param endDate
     *        ending date of the time interval
     * @return
     *        true if the entry is in the specified interval, false otherwise
     */
    boolean isEntryInInterval(TimeSheet entry, LocalDate startDate, LocalDate endDate);    
}