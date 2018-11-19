package com.ideas2it.hrms.dao;

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
 * @author Ganesh Venkat S
 */
public interface TimeSheetDao {
    
    /**
     * Creates a new timesheet entry 
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
     *        requested entry, if exists, null otherwise
     */
    TimeSheet getEntryById(Integer id) throws AppException;
     
    /**
     * Gets all the entries in the timesheet
     * 
     * @return
     *        list of all entries in the timesheet
     */
    List<TimeSheet> getAllEntries() throws AppException;
    
    /**
     * Updates an entry
     * 
     * @param entry
     *        an entry 
     */
    TimeSheet updateEntry(TimeSheet entry) throws AppException;
    
    /**
     * Removes an entry
     * 
     * @param entry
     *        an entry 
     */
    TimeSheet removeEntry(TimeSheet entry) throws AppException;
}