package com.ideas2it.hrms.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.hrms.common.ClentConstants;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Client;
import com.ideas2it.hrms.dao.ClientDao;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.session.HibernateSession;

public class ClientDaoImpl implements ClientDao {
    
    private Session session = null;
    private Transaction transaction = null;
    
    
    public Boolean createClient(Client client) {
        try {
    	    session = HibernateSession.getSession();
            transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ClientConstants.CLIENT_CREATE_ERROR+client.
                getName(), e);
            throw new AppException(ClientConstants.CLIENT_CREATE_ERROR+
                client.getName());
        } finally {
            session.close(); 
        }
    }
    
    public Boolean updateClient(Client client) {
    	try {
    	    session = HibernateSession.getSession();
            transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ClientConstants.CLIENT_CREATE_ERROR+
                client.getName(), e);
            throw new AppException(ClientConstants.CLIENT_UPDATE_ERROR+
                client.getName());
        } finally {
            session.close(); 
        }
    }
    
    public List<Client> fetchClient() {
        List<Client> clients = new ArrayList<Client>();
        try {
            session = HibernateSession.getSession();
            clients = session.createQuery("FROM Client").list();
            return clients;
        } catch (HibernateException e) {
            AppLogger.error(ClientConstants.CLIENT_CREATE_ERROR+
                client.getName(), e);
            throw new AppException(ClientConstants.CLIENT_UPDATE_ERROR+
                client.getName());
        } finally {
            session.close(); 
        }
    }
    
}