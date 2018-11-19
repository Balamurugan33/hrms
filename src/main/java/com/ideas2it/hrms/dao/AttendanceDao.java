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
 * Update attendance, Remove attendance, Get an employee's attendanceSheet 
 * </p>
 *
 * @author Ganesh Venkat S
 */
public interface AttendanceDao {
    
    /**
     * Creates a new attendance entry
     *       
     * @param attendance
     *        new attendance entry
     * @return 
     *        new attendance entry, if added, null otherwise
     */
    Attendance createAttendance(Attendance attendance) throws AppException; 
    
    /**
     * Gets an attendance entry of an employee
     *
     * @param employee
     *        an employee
     * @param entryDate       
     *        a date
     * @return
     *        requested attendance entry, if exists, null otherwise
     */
    Attendance getAttendance(Employee employee, LocalDate entryDate) throws AppException;
    
    /**
     * Gets the attendance sheet of an employee
     * 
     * @param employee
     *        an employee
     * @return
     *        an employee's attendance sheet
     */
    List<Attendance> getAttendanceSheet(Employee employee) throws AppException;

     
    /**
     * Gets the consolidated attendance sheet of all employees in the company
     * 
     * @return
     *        consolidated attendance sheet of all employees
     */
    List<Attendance> getAllAttendances() throws AppException;
    
    /**
     * Updates an attendance entry 
     * 
     * @param attendance
     *        an attendance entry, with updated info
     * @return 
     *        the updated attendance entry        
     */
    Attendance updateAttendance(Attendance attendance) throws AppException;            
}