package com.ideas2it.hrms.session;

import org.hibernate.Session; 
import org.hibernate.cfg.Configuration;

/**
 * Singleton class to manage the creation of database connection
 *
 */ 
public class HibernateSession {

    private static Session session = null;
    
    /* For the sole purpose of preventing external object creation */ 
    private HibernateSession() {
            
    } 

    /* 
     * Synchronized to make it thread safe
     * Creates one instance of the SessionFactory and returns it  
     */
    public static synchronized Session getSession() {
        if (null == session) {
            session = new Configuration().configure().
                buildSessionFactory().openSession();
        }
        return session;
    }  
}