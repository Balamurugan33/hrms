package com.ideas2it.hrms.service.impl;

import java.util.List;

import com.ideas2it.hrms.dao.ClientDao;
import com.ideas2it.hrms.dao.DesignationDao;
import com.ideas2it.hrms.dao.impl.ClientDaoImpl;
import com.ideas2it.hrms.dao.impl.DesignationDaoImpl;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Client;
import com.ideas2it.hrms.model.Designation;
import com.ideas2it.hrms.service.ClientService;
import com.ideas2it.hrms.service.DesignationService;

public class DesignationServiceImpl implements DesignationService{

    private DesignationDao designationDao = new DesignationDaoImpl();
    
    /** {@inheritDoc}*/
    public Boolean createDesignation(Designation designation) 
            throws AppException {
        return designationDao.createDesignation(designation);

    }
    
    /** {@inheritDoc}*/
    public Boolean updateDesignation(Designation designation) 
            throws AppException {
        return designationDao.updateDesignation(designation);

    }
    
    /** {@inheritDoc}*/
    public List<Designation> displayDesignations() throws AppException {
        return designationDao.retrieveDesignations();
    }
    
    /** {@inheritDoc}*/
    public Boolean deleteDesignation(Integer id) throws AppException {
        return designationDao.deleteDesignation(id);
    }
    
    /** {@inheritDoc}*/
    public Boolean isDesignationExist(String name) throws AppException {
        return (null == designationDao.searchDesignation(name));
    }
}