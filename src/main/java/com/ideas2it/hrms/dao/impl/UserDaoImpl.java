package com.ideas2it.hrms.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.hibernate.Query;  
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

import com.ideas2it.hrms.common.UserConstants;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.dao.UserDao;
import com.ideas2it.hrms.model.User;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.session.HibernateSession;

/**
 * <p>
 * The {@code UserDaoImpl} is used to perform the 
 * action on the user
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
public class UserDaoImpl implements UserDao {

    private String insertQuery = 
        "INSERT INTO USER(USER_NAME, PASSWORD, ROLE)"+
        " VALUES(:name, SHA1(:password), :role)";
    
    private Session session = null;
    private Transaction transaction = null;
    
    /** {@inheritDoc}*/
    public Boolean createUser(User user) throws AppException {
        try {
            session = HibernateSession.getSession();
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery(insertQuery);
            query.setParameter("name", user.getUserName());
            query.setParameter("password", user.getPassword());
            query.setParameter("role", user.getRole());
            query.executeUpdate();
            transaction.commit();
            return Boolean.TRUE; 
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(UserConstants.ERROR_CREATE_USER+
                user.getUserName(), e);
            throw new AppException(UserConstants.ERROR_CREATE_USER+
                user.getUserName());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public User searchUser(String userName, String password, String role) 
            throws AppException {
        try {
            session = HibernateSession.getSession();
            String query = "FROM User WHERE userName=:name AND"+
                " password=sha1(:password) AND role=:role";
            Query resultQuery = session.createQuery(query);
            resultQuery.setParameter("name", userName);
            resultQuery.setParameter("password", password);
            resultQuery.setParameter("role", role);
            return (User)resultQuery.uniqueResult();
        } catch (HibernateException e) {
            AppLogger.error(UserConstants.ERROR_SEARCH_USER+userName, e);
            throw new AppException(UserConstants.ERROR_SEARCH_USER+userName);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public User searchName(String userName) throws AppException {
        try {
            session = HibernateSession.getSession();
            String query = "FROM User WHERE userName=:name";
            Query resultQuery = session.createQuery(query);
            resultQuery.setParameter("name", userName);
            return (User)resultQuery.uniqueResult();
        } catch (HibernateException e) {
            AppLogger.error(UserConstants.ERROR_SEARCH_USER+userName, e);
            throw new AppException(UserConstants.ERROR_SEARCH_USER+userName);
        } finally {
            session.close(); 
        }
    }
}