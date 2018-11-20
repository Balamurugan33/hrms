package com.ideas2it.hrms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.hrms.dao.TimeSheetDao;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.model.TimeSheet;
import com.ideas2it.hrms.session.HibernateSession;

import static com.ideas2it.hrms.common.TimeSheetConstants.ERROR_CREATE_ENTRY;
import static com.ideas2it.hrms.common.TimeSheetConstants.ERROR_DELETE_ENTRY;
import static com.ideas2it.hrms.common.TimeSheetConstants.ERROR_RETRIEVE_ENTRIES;
import static com.ideas2it.hrms.common.TimeSheetConstants.ERROR_RETRIEVE_ENTRY;
import static com.ideas2it.hrms.common.TimeSheetConstants.ERROR_UPDATE_ENTRY;

/**
 * <p>
 * Implements TimeSheetDao interface
 * </p>
 *
 * @author Ganesh Venkat S
 */
public class TimeSheetDaoImpl implements TimeSheetDao {
    
    @Override
    public TimeSheet createEntry(TimeSheet entry) throws AppException {
        Transaction transaction = null;

        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            session.save(entry);
            transaction.commit();
        } catch (HibernateException e) {
            AppLogger.error(ERROR_CREATE_ENTRY, e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new AppException(ERROR_CREATE_ENTRY);
        }
        return entry;
    }
    
    @Override
    public TimeSheet getEntryById(Integer id) throws AppException {
        Transaction transaction = null;
        TimeSheet entry = null;
        
        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            entry = (TimeSheet) session.get(TimeSheet.class, id);            
            transaction.commit();
        } catch (HibernateException e) {
            AppLogger.error(ERROR_RETRIEVE_ENTRY, e);
            if (null != transaction) {
                transaction.rollback();
            }  
            throw new AppException(ERROR_RETRIEVE_ENTRY);
        }
        return entry;
    }
    
    @Override
    public List<TimeSheet> getAllEntries() throws AppException {
        Transaction transaction = null;
        List<TimeSheet> consolidatedTimeSheet = new ArrayList<TimeSheet>();
        
        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            consolidatedTimeSheet = session.createQuery("from TimeSheet").list();
            transaction.commit();
        } catch (HibernateException e) {
            AppLogger.error(ERROR_RETRIEVE_ENTRIES, e);
            if (null != transaction) {
                transaction.rollback();
            }  
            throw new AppException(ERROR_RETRIEVE_ENTRIES);
        } 
        return consolidatedTimeSheet;
    }
    
    @Override
    public TimeSheet updateEntry(TimeSheet entry) throws AppException {
        Transaction transaction = null;
        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            session.update(entry);
            transaction.commit();
        } catch (HibernateException e) {
            AppLogger.error(ERROR_UPDATE_ENTRY, e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new AppException(ERROR_UPDATE_ENTRY);
        } 
        return entry;
    }
    
    @Override
    public TimeSheet removeEntry(TimeSheet entry) throws AppException {
        Transaction transaction = null;

        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            session.delete(entry);
            transaction.commit();
        } catch (HibernateException e) {
            AppLogger.error(ERROR_DELETE_ENTRY, e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new AppException(ERROR_DELETE_ENTRY);
        } 
        return entry;
    }        
}
