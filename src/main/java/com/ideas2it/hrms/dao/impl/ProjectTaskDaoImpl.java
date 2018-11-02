package com.ideas2it.hrms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.hrms.dao.ProjectTaskDao;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.model.ProjectTask;
import com.ideas2it.hrms.session.HibernateSession;

import static com.ideas2it.hrms.common.ProjectTaskConstants.ERROR_CREATE_TASK;
import static com.ideas2it.hrms.common.ProjectTaskConstants.ERROR_DELETE_TASK;
import static com.ideas2it.hrms.common.ProjectTaskConstants.ERROR_RETRIEVE_TASK;
import static com.ideas2it.hrms.common.ProjectTaskConstants.ERROR_RETRIEVE_TASKS;
import static com.ideas2it.hrms.common.ProjectTaskConstants.ERROR_UPDATE_TASK;

/**
 * <p>
 * Implements ProjectTaskDao interface
 * </p>
 *
 * @author Ganesh Venkat S
 */
public class ProjectTaskDaoImpl implements ProjectTaskDao {
    
    @Override
    public ProjectTask createTask(ProjectTask task) throws AppException {
        Transaction transaction = null;

        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ERROR_CREATE_TASK + task.getName(), e);
            throw new AppException(ERROR_CREATE_TASK + task.getName());
        }
        return task;
    }
    
    @Override
    public ProjectTask getTaskById(Integer id) throws AppException {
        Transaction transaction = null;
        ProjectTask task = null;
        
        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            task = (ProjectTask) session.get(ProjectTask.class, id);            
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }  
            AppLogger.error(ERROR_RETRIEVE_TASK + task.getName(), e);
            throw new AppException(ERROR_RETRIEVE_TASK + task.getName());
        }
        return task;
    }
    
    @Override
    public List<ProjectTask> getAllTasks() throws AppException {
        Transaction transaction = null;
        List<ProjectTask> tasks = new ArrayList<ProjectTask>();
        
        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            tasks = session.createQuery("from ProjectTask").list();
            transaction.commit();
        } catch (HibernateException e) {
            AppLogger.error(ERROR_RETRIEVE_TASKS, e);
            throw new AppException(ERROR_RETRIEVE_TASKS);
        } 
        return tasks;
    }
    
    @Override
    public ProjectTask updateTask(ProjectTask task) throws AppException {
        Transaction transaction = null;

        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            session.update(task);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ERROR_UPDATE_TASK + task.getName(), e);
            throw new AppException(ERROR_UPDATE_TASK + task.getName());
        } 
        return task;
    }
    
    @Override
    public ProjectTask removeTask(ProjectTask task) throws AppException {
        Transaction transaction = null;

        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            session.delete(task);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ERROR_DELETE_TASK + task.getName(), e);
            throw new AppException(ERROR_DELETE_TASK + task.getName());
        } 
        return task;
    }        
}
