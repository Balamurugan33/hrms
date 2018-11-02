package com.ideas2it.hrms.dao;

import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Client;

public interface ClientDao {
    
    /**
     * Creates a new client 
     * 
     * @param client
     *       Used get the client details
     */
    Boolean createClient(Client client) throws AppException;
    
    /**
     * Update an existing client 
     * 
     * @param client
     *       Used get the client details
     */
    Boolean updateClient(Client client) throws AppException;
    
    /**
     * get all the existing client 
     */
    List<Client> retrieveClients() throws AppException;
    
    /**
     * remove an existing client 
     * 
     * @param id
     *       client id
     */
    Boolean deleteClient(Integer id) throws AppException;
}