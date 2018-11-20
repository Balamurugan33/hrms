package com.ideas2it.hrms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.hrms.common.DesignationConstants;
import com.ideas2it.hrms.dao.DesignationDao;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.model.Designation;
import com.ideas2it.hrms.session.HibernateSession;

public class DesignationDaoImpl implements DesignationDao {
    
    private Session session = null;
    private Transaction transaction = null;
    
    /** {@inheritDoc}*/
    public Boolean createDesignation(Designation designation) 
            throws AppException {
        try {
            session = HibernateSession.getSession();
            transaction = session.beginTransaction();
            session.save(designation);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(DesignationConstants.
                ERROR_CREATE_DESIGNATION+designation.getName(), e);
            throw new AppException(DesignationConstants.ERROR_CREATE_DESIGNATION
                +designation.getName());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean updateDesignation(Designation designation) 
            throws AppException {
        try {
            session = HibernateSession.getSession();
            transaction = session.beginTransaction();
            session.update(designation);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(DesignationConstants.ERROR_UPDATE_DESIGNATION+
                designation.getName(), e);
            throw new AppException(DesignationConstants.
                ERROR_UPDATE_DESIGNATION+designation.getName());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public List<Designation> retrieveDesignations() throws AppException {
        List<Designation> designations = new ArrayList<Designation>();
        try {
            session = HibernateSession.getSession();
            session.enableFilter("designationFilter");
            designations = session.createQuery("FROM Designation").list();
            return designations;
        } catch (HibernateException e) {
            AppLogger.error(DesignationConstants.ERROR_RETRIEVE_DESIGNATION, e);
            throw new AppException(DesignationConstants.
                ERROR_RETRIEVE_DESIGNATION);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean deleteDesignation(Integer id) throws AppException {
        try {
            session = HibernateSession.getSession();
            transaction = session.beginTransaction();
            Designation designation 
                = (Designation)session.get(Designation.class, id);
            session.delete(designation);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(DesignationConstants.ERROR_DELETE_DESIGNATION+id, e);
            throw new AppException(DesignationConstants.
                ERROR_DELETE_DESIGNATION+id);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc} */
    public Designation searchDesignation(String name) throws AppException {
        try {
            session = HibernateSession.getSession();
            String query = "FROM Designation WHERE name = :name";
            return (Designation)session.createQuery(query).
                setParameter("name", name ).uniqueResult();
        } catch (HibernateException e) {
            AppLogger.error(DesignationConstants.ERROR_RETRIEVE_DESIGNATION, e);
            throw new AppException(DesignationConstants.
                ERROR_RETRIEVE_DESIGNATION);
        } finally {
            session.close(); 
        }
    }
}