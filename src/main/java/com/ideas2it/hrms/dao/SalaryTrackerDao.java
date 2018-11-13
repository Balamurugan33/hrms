package com.ideas2it.hrms.dao;

import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.SalaryTracker;

/**
 * Used to perform the CRUD operation on database 
 * for salaryTracker details 
 * 
 * @version 1
 * @author Balamurugan M
 */
public interface SalaryTrackerDao {
    
    /**
     * Creates a new salaryTracker 
     * 
     * @param salaryTracker
     *        Used get the salaryTracker details
     */
    Boolean createSalaryTracker(SalaryTracker salaryTracker) throws AppException;
    
    /**
     * Update an existing salaryTracker 
     * 
     * @param salaryTracker
     *        Used get the salaryTracker details
     */
    Boolean updateSalaryTracker(SalaryTracker salaryTracker) throws AppException;
    
    /** 
     * Get the particular salaryTracker
     * @param name
     *        Used to get the salaryTracker name
     */
    SalaryTracker searchSalaryTracker(String name) throws AppException;
    
    /**
     * Remove an existing salaryTracker 
     * 
     * @param id
     *        salaryTracker id
     */
    Boolean deleteSalaryTracker(Integer id) throws AppException;
    
    /**
     * Get all the existing salaryTrackers
     */
    List<SalaryTracker> retrieveSalaryTrackers() throws AppException;
    
}