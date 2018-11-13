package com.ideas2it.hrms.dao;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Attendance;
import com.ideas2it.hrms.model.Employee;

/**
 * <p>
 * Provides an interface for basic CRUD operations on the Attendance Model:
 * Add new attendance, Get attendance, Get all attendances,
 * Update attendance, Remove attendance
 * </p>
 *
 * @author Ganesh Venkat S
 */
public interface AttendanceDao {
    
    /**
     * Creates a new attendance 
     * 
     * @param employee
     *       employee
     *       
     * @param attendance
     *       new attendance
     * @return 
     *       new attendance, if added, null otherwise
     */
    Attendance createAttendance(Attendance attendance) throws AppException; 
    
    /**
     * Gets a today's attendance entry of an employee
     *
     * @param employee
     *        employee
     * @param entryDate       
     *        an entry date of an employee's attendance history
     * @return
     *        requested attendance, if exists, null otherwise
     */
    Attendance getAttendance(Employee employee, LocalDate entryDate) throws AppException;
    
    /**
     * Gets the attendance sheet of an employee
     * 
     * @return
     *        attendance sheet
     */
    List<Attendance> getAttendanceSheet(Employee employee) throws AppException;

     
    /**
     * Gets the attendances of all employees in company
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
}