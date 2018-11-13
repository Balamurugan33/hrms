package com.ideas2it.hrms.dao.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.hrms.dao.AttendanceDao;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.model.Attendance;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.session.HibernateSession;

import static com.ideas2it.hrms.common.AttendanceConstants.ERROR_CREATE_ATTENDANCE;
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
    
    public Attendance getAttendance(Employee employee, LocalDate attendDate) throws AppException {
        Transaction transaction = null;
        Attendance attendance = null;
        
        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Attendance> criteria = builder.createQuery(Attendance.class);
            Root<Attendance> attendRoot = criteria.from(Attendance.class);

            Predicate[] predicates = new Predicate[2];
            predicates[0] = builder.equal(attendRoot.get("employee"), employee);
            predicates[1] = builder.equal(attendRoot.get("attendDate"), attendDate);
            criteria.select(attendRoot).where(predicates);
            attendance = session.createQuery(criteria).getSingleResult();
            transaction.commit();
        } catch (HibernateException e) {
            AppLogger.error(ERROR_RETRIEVE_ATTENDANCE + attendance.getId(), e);

            if (null != transaction) {
                transaction.rollback();
            }  
            throw new AppException(ERROR_RETRIEVE_ATTENDANCE 
                + attendance.getId());
        }
        return attendance;
    }
    
    public List<Attendance> getAttendanceSheet(Employee employee) throws AppException {
        Transaction transaction = null;
        Integer empId = employee.getId();
        List<Attendance> attendanceSheet = new ArrayList<Attendance>();
        
        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            
            String getAttendanceSheet = "from Attendance where employee.id = :employee_id";            
            attendanceSheet = session.createQuery(getAttendanceSheet).setParameter("employee_id", empId).getResultList();           
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ERROR_RETRIEVE_ATTENDANCES + employee.getId(), e);
            throw new AppException(ERROR_RETRIEVE_ATTENDANCES 
                + employee.getId());
        }
        return attendanceSheet;
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
}
