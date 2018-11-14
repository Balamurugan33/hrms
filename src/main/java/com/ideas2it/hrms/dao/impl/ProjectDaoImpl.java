package com.ideas2it.hrms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.hrms.dao.ProjectDao;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.session.HibernateSession;

import static com.ideas2it.hrms.common.ProjectConstants.ERROR_CREATE_PROJECT;
import static com.ideas2it.hrms.common.ProjectConstants.ERROR_DELETE_PROJECT;
import static com.ideas2it.hrms.common.ProjectConstants.ERROR_RETRIEVE_PROJECT;
import static com.ideas2it.hrms.common.ProjectConstants.ERROR_RETRIEVE_PROJECTS;
import static com.ideas2it.hrms.common.ProjectConstants.ERROR_UPDATE_PROJECT;

/**
 * <p>
 * Implements ProjectDao interface
 * </p>
 *
 * @author Ganesh Venkat S
 */
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
            AppLogger.error(ERROR_CREATE_PROJECT + project.getName(), e);
            throw new AppException(ERROR_CREATE_PROJECT + project.getName());
        }
        return project;
    }
    
    @Override
    public Project getProjectById(Integer id) throws AppException {
        Transaction transaction = null;
        Project project = null;
        
        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            project = (Project) session.get(Project.class, id);            
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }  
            AppLogger.error(ERROR_RETRIEVE_PROJECT + project.getName(), e);
            throw new AppException(ERROR_RETRIEVE_PROJECT + project.getName());
        }
        return project;
    }
    
    @Override
    public List<Project> getAllProjects() throws AppException {
        Transaction transaction = null;
        List<Project> projects = new ArrayList<Project>();
        
        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            projects = session.createQuery("from Project").list();
            transaction.commit();
        } catch (HibernateException e) {
            AppLogger.error(ERROR_RETRIEVE_PROJECTS, e);
            throw new AppException(ERROR_RETRIEVE_PROJECTS);
        } 
        return projects;
    }
    
    @Override
    public Project updateProject(Project project) throws AppException {
        Transaction transaction = null;

        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            session.update(project);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ERROR_UPDATE_PROJECT + project.getName(), e);
            throw new AppException(ERROR_UPDATE_PROJECT + project.getName());
        } 
        return project;
    }
    
    @Override
    public Project removeProject(Project project) throws AppException {
        Transaction transaction = null;

        try (Session session = HibernateSession.getSession()) {
            transaction = session.beginTransaction();
            session.delete(project);
            transaction.commit();
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ERROR_DELETE_PROJECT + project.getName(), e);
            throw new AppException(ERROR_DELETE_PROJECT + project.getName());
        } 
        return project;
    }        
}
