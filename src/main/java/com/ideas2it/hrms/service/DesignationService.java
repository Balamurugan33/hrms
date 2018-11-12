package com.ideas2it.hrms.service;

import java.util.List;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Client;
import com.ideas2it.hrms.model.Designation;

/**
 * It's act as the intermediate between the controller and dao
 * And it's used to perform  all business logic   
 * 
 * @version 1
 * @author Balamurugan M
 */
public interface DesignationService {
    
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
    * Get all the existing Designations
    */
   List<Designation> displayDesignations() throws AppException;
   
   /**
    * Remove an existing designation 
    * 
    * @param id
    *        Used to get the designation id
    */
   Boolean deleteDesignation(Integer id) throws AppException;
   
   /**
    * Used check the designation is exist or not
    * @param name
    *        Get the designation name
    */
   Boolean isDesignationExist(String name) throws AppException;
   
}