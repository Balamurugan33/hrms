package com.ideas2it.hrms.dao;

import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Designation;

/**
 * Used to perform the CRUD operation on database 
 * for designation details 
 * 
 * @version 1
 * @author Balamurugan M
 */
public interface DesignationDao {
    
    /**
     * Creates a new designation 
     * 
     * @param designation
     *        Used get the designation details
     */
    Boolean createDesignation(Designation designation) throws AppException;
    
    /**
     * Update an existing designation 
     * 
     * @param designation
     *        Used get the designation details
     */
    Boolean updateDesignation(Designation designation) throws AppException;
    
    /** 
     * Get the particular designation
     * @param name
     *        Used to get the designation name
     */
    Designation searchDesignation(String name) throws AppException;
    
    /**
     * Remove an existing designation 
     * 
     * @param id
     *        designation id
     */
    Boolean deleteDesignation(Integer id) throws AppException;
    
    /**
     * Get all the existing designations
     */
    List<Designation> retrieveDesignations() throws AppException;
    
}