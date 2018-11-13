package com.ideas2it.hrms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.hrms.common.SalaryTrackerConstants;
import com.ideas2it.hrms.dao.SalaryTrackerDao;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.model.SalaryTracker;
import com.ideas2it.hrms.session.HibernateSession;

public class SalaryTrackerDaoImpl implements SalaryTrackerDao {
    
    private Session session = null;
    private Transaction transaction = null;
    
    /** {@inheritDoc}*/
    public Boolean createSalaryTracker(SalaryTracker salaryTracker) 
            throws AppException {
        try {
            session = HibernateSession.getSession();
            transaction = session.beginTransaction();
            session.save(salaryTracker);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(SalaryTrackerConstants.
                ERROR_CREATE_DESIGNATION+salaryTracker.getId(), e);
            throw new AppException(SalaryTrackerConstants.ERROR_CREATE_DESIGNATION
                +salaryTracker.getId());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean updateSalaryTracker(SalaryTracker salaryTracker) 
            throws AppException {
        try {
            session = HibernateSession.getSession();
            transaction = session.beginTransaction();
            session.update(salaryTracker);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(SalaryTrackerConstants.ERROR_UPDATE_DESIGNATION+
                salaryTracker.getId(), e);
            throw new AppException(SalaryTrackerConstants.
                ERROR_UPDATE_DESIGNATION+salaryTracker.getId());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public List<SalaryTracker> retrieveSalaryTrackers() throws AppException {
        List<SalaryTracker> salaryTrackers = new ArrayList<SalaryTracker>();
        try {
            session = HibernateSession.getSession();
            salaryTrackers = session.createQuery("FROM SalaryTracker").list();
            return salaryTrackers;
        } catch (HibernateException e) {
            AppLogger.error(SalaryTrackerConstants.ERROR_RETRIEVE_DESIGNATION, e);
            throw new AppException(SalaryTrackerConstants.
                ERROR_RETRIEVE_DESIGNATION);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean deleteSalaryTracker(Integer id) throws AppException {
        try {
            session = HibernateSession.getSession();
            transaction = session.beginTransaction();
            SalaryTracker salaryTracker 
                = (SalaryTracker)session.get(SalaryTracker.class, id);
            session.delete(salaryTracker);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(SalaryTrackerConstants.ERROR_DELETE_DESIGNATION+id, e);
            throw new AppException(SalaryTrackerConstants.
                ERROR_DELETE_DESIGNATION+id);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc} */
    public SalaryTracker searchSalaryTracker(String name) throws AppException {
        try {
            session = HibernateSession.getSession();
            String query = "FROM SalaryTracker WHERE name = :name";
            return (SalaryTracker)session.createQuery(query).
                setParameter("name", name ).uniqueResult();
        } catch (HibernateException e) {
            AppLogger.error(SalaryTrackerConstants.ERROR_RETRIEVE_DESIGNATION, e);
            throw new AppException(SalaryTrackerConstants.
                ERROR_RETRIEVE_DESIGNATION);
        } finally {
            session.close(); 
        }
    }
}