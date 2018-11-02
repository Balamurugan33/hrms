package com.ideas2it.hrms.dao;

import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Client;

/**
 * Used to perform the CRUD operation on database 
 * for client details 
 * 
 * @version 1
 * @author Balamurugan M
 */
public interface ClientDao {
    
    /**
     * Creates a new client 
     * 
     * @param client
     *        Used get the client details
     */
    Boolean createClient(Client client) throws AppException;
    
    /**
     * Update an existing client 
     * 
     * @param client
     *        Used get the client details
     */
    Boolean updateClient(Client client) throws AppException;
    
    /**
     * Get all the existing client 
     */
    List<Client> retrieveClients() throws AppException;
    
    /**
     * Remove an existing client 
     * 
     * @param id
     *        client id
     */
    Boolean deleteClient(Integer id) throws AppException;
    
    /** 
     * Get the particular client
     * @param email
     *        Used to get the client email id
     */
    Client searchClient(String email) throws AppException;
}