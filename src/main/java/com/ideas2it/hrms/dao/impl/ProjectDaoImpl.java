package com.ideas2it.hrms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

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
            AppLogger.error(ERROR_CREATE_PROJECT + project.getName(), e);
            if (null != transaction) {
                transaction.rollback();
            }
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
            
            
            
            
            
            //Query query = session.getNamedQuery("getProjectById").setParameter(1, id);
            /* 
             *@NamedNativeQuery(
    name = "getProjectById",
    query = "select * from project where id = ?"
    ) 
             */
            
            //query.executeQuery();
            //project = (Project) session.get(Project.class, id);            
          /*  Query query = session.createSQLQuery(
                "select * from stock s where s.stock_code = :stockCode")
                .addEntity(Stock.class)
                .setParameter("stockCode", "7277");
                List result = query.list();
            
            
            String sql = "select * from project where id = ";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(Employee.class);
            List employees = query.list();

            for (Iterator iterator = employees.iterator(); iterator.hasNext();){
               Employee employee = (Employee) iterator.next(); 
               System.out.print("First Name: " + employee.getFirstName()); 
               System.out.print("  Last Name: " + employee.getLastName()); 
               System.out.println("  Salary: " + employee.getSalary()); 
            }
            
            
            
            String sqlQuery = "select * from project where id = :id";
            SQLQuery getProject = session.createSQLQuery(sqlQuery);
            
            Query query = session.createSQLQuery(
                
                ) */
            
            //Book b = (Book) em.createNativeQuery("SELECT * FROM book b WHERE id = 1", Book.class).getSingleResult();
            //Project p = (Project) session.createNativeQuery("select * from project p where id = id", Project.class).getSingleResult();
            
            Query query = session.createSQLQuery("select * from project p where p.id = :id")
                .addEntity(Project.class).setParameter("id", id);
            project = (Project) query.getSingleResult();
            
            
            //project = (Project) session.get(Project.class, id);            
            
            transaction.commit();
        } catch (HibernateException e) {
            AppLogger.error(ERROR_RETRIEVE_PROJECT + project.getName(), e);
            if (null != transaction) {
                transaction.rollback();
            }  
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
            // Gets only the active projects
            projects = session.createQuery("from Project").list();
            transaction.commit();
        } catch (HibernateException e) {
            AppLogger.error(ERROR_RETRIEVE_PROJECTS, e);
            if (null != transaction) {
                transaction.rollback();
            } 
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
            AppLogger.error(ERROR_UPDATE_PROJECT + project.getName(), e);
            if (null != transaction) {
                transaction.rollback();
            }
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
            AppLogger.error(ERROR_DELETE_PROJECT + project.getName(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw new AppException(ERROR_DELETE_PROJECT + project.getName());
        } 
        return project;
    }        
}
