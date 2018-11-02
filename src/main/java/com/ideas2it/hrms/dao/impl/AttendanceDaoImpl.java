package com.ideas2it.hrms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.hrms.dao.AttendanceDao;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.model.Attendance;
import com.ideas2it.hrms.session.HibernateSession;

import static com.ideas2it.hrms.common.AttendanceConstants.ERROR_CREATE_ATTENDANCE;
import static com.ideas2it.hrms.common.AttendanceConstants.ERROR_DELETE_ATTENDANCE;
import static com.ideas2it.hrms.common.AttendanceConstants.ERROR_RETRIEVE_ATTENDANCE;
import static com.ideas2it.hrms.common.AttendanceConstants.ERROR_RETRIEVE_ATTENDANCES;
import static com.ideas2it.hrms.common.AttendanceConstants.ERROR_UPDATE_ATTENDANCE;

/**
 * <p>
 * Implements AttendanceDao interface
 * </p>
 *
 * @author Ganesh Venkat S
 */
public class AttendanceDaoImpl implements AttendanceDao {
    
    @Override
    public Attendance createAttendance(Attendance attendance) 
            throws AppException {
        Transaction transaction = null;

        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            session.save(attendance);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ERROR_CREATE_ATTENDANCE + attendance.getId(), e);
            throw new AppException(ERROR_CREATE_ATTENDANCE 
                + attendance.getId());
        }
        return attendance;
    }
    
    @Override
    public Attendance getAttendanceById(Integer id) throws AppException {
        Transaction transaction = null;
        Attendance attendance = null;
        
        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            attendance = (Attendance) session.get(Attendance.class, id);            
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }  
            AppLogger.error(ERROR_RETRIEVE_ATTENDANCE + attendance.getId(), e);
            throw new AppException(ERROR_RETRIEVE_ATTENDANCE 
                + attendance.getId());
        }
        return attendance;
    }
    
    @Override
    public List<Attendance> getAllAttendances() throws AppException {
        Transaction transaction = null;
        List<Attendance> attendance = new ArrayList<Attendance>();
        
        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            attendance = session.createQuery("from Attendance").list();
            transaction.commit();
        } catch (HibernateException e) {
            AppLogger.error(ERROR_RETRIEVE_ATTENDANCES, e);
            throw new AppException(ERROR_RETRIEVE_ATTENDANCES);
        } 
        return attendance;
    }
    
    @Override
    public Attendance updateAttendance(Attendance attendance) 
        throws AppException {
        Transaction transaction = null;

        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            session.update(attendance);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ERROR_UPDATE_ATTENDANCE + attendance.getId(), e);
            throw new AppException(ERROR_UPDATE_ATTENDANCE 
                + attendance.getId());
        } 
        return attendance;
    }
    
    @Override
    public Attendance removeAttendance(Attendance attendance) 
        throws AppException {
        Transaction transaction = null;

        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            session.delete(attendance);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ERROR_DELETE_ATTENDANCE + attendance.getId(), e);
            throw new AppException(ERROR_DELETE_ATTENDANCE 
                + attendance.getId());
        } 
        return attendance;
    }        
}
