package com.ideas2it.hrms.service.impl;

import java.util.List;

import com.ideas2it.hrms.dao.AttendanceDao;
import com.ideas2it.hrms.dao.impl.AttendanceDaoImpl;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Attendance;
import com.ideas2it.hrms.service.AttendanceService;

/**
 * <p>
 * Implements AttendanceService interface
 * </p>
 *
 * @author Ganesh Venkat S
 */
public class AttendanceServiceImpl implements AttendanceService {
    
    public Attendance createAttendance(Attendance attendance) throws AppException {
        AttendanceDao attendanceDao = new AttendanceDaoImpl();
        return attendanceDao.createAttendance(attendance);
    }
    
    public Attendance getAttendanceById(Integer id) throws AppException {
        AttendanceDao attendanceDao = new AttendanceDaoImpl();
        return attendanceDao.getAttendanceById(id);
    }
    
    public List<Attendance> getAllAttendances() throws AppException {
        AttendanceDao attendanceDao = new AttendanceDaoImpl();
        return attendanceDao.getAllAttendances();
    }
    
    public Attendance updateAttendance(Attendance attendance) throws AppException {
        AttendanceDao attendanceDao = new AttendanceDaoImpl();
        return attendanceDao.updateAttendance(attendance);
    }

    public Attendance removeAttendance(Attendance attendance) throws AppException {
        AttendanceDao attendanceDao = new AttendanceDaoImpl();
        return attendanceDao.removeAttendance(attendance);
    }
}
