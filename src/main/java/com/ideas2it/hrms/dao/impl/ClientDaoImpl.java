package com.ideas2it.hrms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.hrms.common.ClientConstants;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Client;
import com.ideas2it.hrms.dao.ClientDao;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.session.HibernateSession;

public class ClientDaoImpl implements ClientDao {
    
    private Session session = null;
    private Transaction transaction = null;
    
    /** {@inheritDoc}*/
    public Boolean createClient(Client client) throws AppException {
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
            AppLogger.error(ClientConstants.ERROR_CREATE_CLIENT+client.
                getName(), e);
            throw new AppException(ClientConstants.ERROR_CREATE_CLIENT+
                client.getName());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean updateClient(Client client) throws AppException {
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
            AppLogger.error(ClientConstants.ERROR_UPDATE_CLIENT+
                client.getName(), e);
            throw new AppException(ClientConstants.ERROR_UPDATE_CLIENT+
                client.getName());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public List<Client> retrieveClients() throws AppException {
        List<Client> clients = new ArrayList<Client>();
        try {
            session = HibernateSession.getSession();
            clients = session.createQuery("FROM Client").list();
            return clients;
        } catch (HibernateException e) {
            AppLogger.error(ClientConstants.ERROR_RETRIEVE_CLIENT, e);
            throw new AppException(ClientConstants.ERROR_RETRIEVE_CLIENT);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean deleteClient(Integer id) throws AppException {
        try {
    	    session = HibernateSession.getSession();
            transaction = session.beginTransaction();
            Client client = (Client)session.get(Client.class, id);
            session.delete(client);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(ClientConstants.ERROR_DELETE_CLIENT+id, e);
            throw new AppException(ClientConstants.ERROR_DELETE_CLIENT+id);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc} */
    public Client searchClient(String email) throws AppException {
        try {
            session = HibernateSession.getSession();
            String query = "FROM Client WHERE emailId = :email";
            return (Client)session.createQuery(query).
                setParameter("email", email ).uniqueResult();
        } catch (HibernateException e) {
            AppLogger.error(ClientConstants.ERROR_RETRIEVE_CLIENT, e);
            throw new AppException(ClientConstants.ERROR_RETRIEVE_CLIENT);
        } finally {
            session.close(); 
        }
    }
}