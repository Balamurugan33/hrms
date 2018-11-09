package com.ideas2it.hrms.session;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.logger.AppLogger;

/**
 * Singleton class to manage the creation of database connection
 *
 */ 
public class HibernateSession {

    private static final String SESSION_CREATE_ERROR
        = "Failed to create Session factory";
    
    private static SessionFactory factory;
    
    /* For the sole purpose of preventing external object creation */ 
    private HibernateSession() {
            
    }

    /* 
     * Creates one instance of the Session and returns it  
     */
    public static Session getSession() throws AppException {
        try {
            if (null == factory) {
                factory = new Configuration().configure().buildSessionFactory();
            }
            return factory.openSession();
        } catch (HibernateException e) {
            AppLogger.error(SESSION_CREATE_ERROR, e);
            throw new AppException(SESSION_CREATE_ERROR + e);
        }
    }
}