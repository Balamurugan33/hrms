package com.ideas2it.hrms.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.SalaryTracker;

public interface SalaryTrackerService {
    
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
     * Get all the existing SalaryTrackers
     */
    List<SalaryTracker> displaySalaryTrackers() throws AppException;
    
    /**
     * Remove an existing salaryTracker 
     * 
     * @param id
     *        Used to get the salaryTracker id
     */
    Boolean deleteSalaryTracker(Integer id) throws AppException;
    
    /**
     * Used check the salaryTracker is exist or not
     * @param name
     *        Get the salaryTracker name
     */
    Boolean isSalaryTrackerExist(String name) throws AppException;
    
    /**
     * Used to get the salary tracker of particular date
     * 
     * @param workedDate
     *        date to get the employee salary tracker
     * @param salaryTrackers
     */
    SalaryTracker getSalaryTrackerOnDate(LocalDate workedDate, 
        List<SalaryTracker> salaryTrackers);
}