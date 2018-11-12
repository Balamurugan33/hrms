package com.ideas2it.hrms.service.impl;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.hrms.dao.AttendanceDao;
import com.ideas2it.hrms.dao.impl.AttendanceDaoImpl;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Attendance;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.service.AttendanceService;

/**
 * <p>
 * Implements AttendanceService interface
 * </p>
 *
 * @author Ganesh Venkat S
 */
public class AttendanceServiceImpl implements AttendanceService {
    
    public List<Attendance> markPresent(Employee employee) throws AppException {
        AttendanceDao attendanceDao = new AttendanceDaoImpl();
        Attendance attendance;
        LocalDate today = LocalDate.now();
        
        attendance = getAttendance(employee);
        if (null != attendance) {
            attendance.setStatus(true);
            attendance = updateAttendance(attendance);
        } else {
            attendance = new Attendance();
            attendance.setEmployee(employee);
            attendance.setAttendDate(today);
            attendance.setStatus(true);
            attendance = createAttendance(attendance);
        }

        return attendanceDao.getAttendanceSheet(employee);
    }
    
    public List<Attendance> markAbsent(Employee employee) throws AppException {
        AttendanceDao attendanceDao = new AttendanceDaoImpl();
        Attendance attendance;
        LocalDate today = LocalDate.now();

        attendance = getAttendance(employee);
        attendance.setStatus(false);
        updateAttendance(attendance);

        return attendanceDao.getAttendanceSheet(employee);
    }
        
    public Attendance createAttendance(Attendance attendance) throws AppException {
        AttendanceDao attendanceDao = new AttendanceDaoImpl();
        return attendanceDao.createAttendance(attendance);
    }
    
    public Attendance getAttendance(Employee employee) throws AppException {
        AttendanceDao attendanceDao = new AttendanceDaoImpl();
        return attendanceDao.getAttendance(employee);
    }
    
    public List<Attendance> getAttendanceSheet(Employee employee) throws AppException {
        AttendanceDao attendanceDao = new AttendanceDaoImpl();
        return attendanceDao.getAttendanceSheet(employee);        
    }
    
    public List<Attendance> getAllAttendances() throws AppException {
        AttendanceDao attendanceDao = new AttendanceDaoImpl();
        return attendanceDao.getAllAttendances();
    }
        
    public Attendance updateAttendance(Attendance attendance) throws AppException {
        AttendanceDao attendanceDao = new AttendanceDaoImpl();
        return attendanceDao.updateAttendance(attendance);
    }
}
