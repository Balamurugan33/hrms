package com.ideas2it.hrms.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Client;

/**
 * It's act as the intermediate between the controller and dao
 * And it's used to perform  all business logic   
 * 
 * @version 1
 * @author Balamurugan M
 */
public interface ClientService {
    
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
    List<Client> displayClients() throws AppException;
    
    /**
     * Remove an existing client 
     * 
     * @param id
     *        Used to get the client id
     */
    Boolean deleteClient(Integer id) throws AppException;
    
    /**
     * Used check the client is exist or not
     * @param email
     *        Get the client mail id
     */
    Boolean isClientExist(String email) throws AppException;
    
    /**
     * Calculates net profit for company from this client, for current month
     * @param email
     *        client
     * @throws AppException 
     */
    Integer calculateNetProfit(Client client, LocalDate startDate, LocalDate endDate) throws AppException;
    
    /**
     * Find an existing client 
     */
    Client searchClient(String email) throws AppException;
}
