package com.ideas2it.hrms.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.hrms.common.ProjectConstants;
import com.ideas2it.hrms.dao.ProjectDao;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.session.HibernateSession;


public class ProjectDaoImpl implements ProjectDao {
    
    @Override
    public Project createProject(Project project) throws AppException {
        Transaction transaction = null;

        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            session.save(project);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ProjectConstants.ERROR_CREATE_PROJECT 
                + project.getName(), e);
            throw new AppException(ProjectConstants.ERROR_CREATE_PROJECT +
                project.getName());
        }
        return project;
    }
    
    @Override
    public Project getProjectById(Integer id) throws AppException {
        Transaction transaction = null;
        Project project;
        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            project = (Project) session.get(Project.class, id);            
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }  
            AppLogger.error(ProjectConstants.ERROR_RETRIEVE_PROJECT 
                + project.getName(), e);
            throw new AppException(ProjectConstants.ERROR_RETRIEVE_PROJECT +
                project.getName());
        }
        return project;
    }
     
    @Override
    public Project getProjectById(Integer id) throws AppException {
        Transaction transaction = null;
        Project project;
        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            project = (Project) session.get(Project.class, id);            
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }  
            AppLogger.error(ProjectConstants.ERROR_RETRIEVE_PROJECT 
                + project.getName(), e);
            throw new AppException(ProjectConstants.ERROR_RETRIEVE_PROJECT +
                project.getName());
        }
        return project;
    }
    

    
    
    
}
