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

import static com.ideas2it.hrms.common.TimeSheetConstants.ERROR_CREATE_TASK;
import static com.ideas2it.hrms.common.TimeSheetConstants.ERROR_DELETE_TASK;
import static com.ideas2it.hrms.common.TimeSheetConstants.ERROR_RETRIEVE_TASK;
import static com.ideas2it.hrms.common.TimeSheetConstants.ERROR_RETRIEVE_TASKS;
import static com.ideas2it.hrms.common.TimeSheetConstants.ERROR_UPDATE_TASK;

/**
 * <p>
 * Implements TimeSheetDao interface
 * </p>
 *
 * @author Ganesh Venkat S
 */
public class TimeSheetDaoImpl implements TimeSheetDao {
    
    @Override
    public TimeSheet createTask(TimeSheet task) throws AppException {
        Transaction transaction = null;

        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ERROR_CREATE_TASK, e);
            throw new AppException(ERROR_CREATE_TASK );
        }
        return task;
    }
    
    @Override
    public TimeSheet getTaskById(Integer id) throws AppException {
        Transaction transaction = null;
        TimeSheet task = null;
        
        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            task = (TimeSheet) session.get(TimeSheet.class, id);            
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }  
            AppLogger.error(ERROR_RETRIEVE_TASK , e);
            throw new AppException(ERROR_RETRIEVE_TASK );
        }
        return task;
    }
    
    @Override
    public List<TimeSheet> getAllTasks() throws AppException {
        Transaction transaction = null;
        List<TimeSheet> tasks = new ArrayList<TimeSheet>();
        
        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            tasks = session.createQuery("from TimeSheet").list();
            transaction.commit();
        } catch (HibernateException e) {
            AppLogger.error(ERROR_RETRIEVE_TASKS, e);
            throw new AppException(ERROR_RETRIEVE_TASKS);
        } 
        return tasks;
    }
    
    @Override
    public TimeSheet updateTask(TimeSheet task) throws AppException {
        Transaction transaction = null;

        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            session.update(task);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ERROR_UPDATE_TASK , e);
            throw new AppException(ERROR_UPDATE_TASK );
        } 
        return task;
    }
    
    @Override
    public TimeSheet removeTask(TimeSheet task) throws AppException {
        Transaction transaction = null;

        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            session.delete(task);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ERROR_DELETE_TASK, e);
            throw new AppException(ERROR_DELETE_TASK);
        } 
        return task;
    }        
}
