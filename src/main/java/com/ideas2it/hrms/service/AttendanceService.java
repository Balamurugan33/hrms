package com.ideas2it.hrms.service;

import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Attendance;

/**
 * <p>
 * Provides an interface for basic CRUD operations on the Attendance Model:
 * Add new attendance, Get attendance, Get all attendances,
 * Update attendance, Remove attendance
 * </p>
 *
 * @author Ganesh Venkat S
 */
public interface AttendanceService {
    
    /**
     * Creates a new attendance 
     * 
     * @param attendance
     *       new attendance
     * @return 
     *       new attendance, if added, null otherwise
     */
    Attendance createAttendance(Attendance attendance) throws AppException;
    
    /**
     * Gets a attendance 
     *
     * @param id
     *        id of the requested attendance
     * @return
     *        requested attendance, if exists, null otherwise
     */
    Attendance getAttendanceById(Integer id) throws AppException;
    
    /**
     * Gets all the attendances allocated to company
     * 
     * @return
     *        list of all attendances
     */
    List<Attendance> getAllAttendances() throws AppException;
    
    /**
     * Updates a attendance's details
     * 
     * @param attendance
     *       attendance to update
     */
    Attendance updateAttendance(Attendance attendance) throws AppException;
    
    /**
     * Removes a attendance
     * 
     * @param attendance
     *       attendance to remove
     */
    Attendance removeAttendance(Attendance attendance) throws AppException;
}