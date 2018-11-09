package com.ideas2it.hrms.service.impl;

import java.util.List;

import com.ideas2it.hrms.dao.ClientDao;
import com.ideas2it.hrms.dao.impl.ClientDaoImpl;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Client;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.service.ClientService;
import com.ideas2it.hrms.service.ProjectService;

public class ClientServiceImpl implements ClientService {
    
    private ClientDao clientDao = new ClientDaoImpl();
    
    /** {@inheritDoc}*/
    public Boolean createClient(Client client) throws AppException {
        return clientDao.createClient(client);

    }
    
    /** {@inheritDoc}*/
    public Boolean updateClient(Client client) throws AppException {
        return clientDao.updateClient(client);

    }
    
    /** {@inheritDoc}*/
    public List<Client> displayClients() throws AppException {
        return clientDao.retrieveClients();
    }
    
    /** {@inheritDoc}*/
    public Boolean deleteClient(Integer id) throws AppException {
        return clientDao.deleteClient(id);
    }
    
    /** {@inheritDoc}*/
    public Boolean isClientExist(String email) throws AppException {
        return (null == clientDao.searchClient(email));
    }
    
    /** {@inheritDoc}*/
    public Integer calculateNetProfit(Client client) {
        ProjectService projectService = new ProjectServiceImpl();
        Integer billableAmount = 0;
        for(Project project : client.getProjects()) {
            /*billableAmount 
                = billableAmount + projectService.calculateNetProfit(project);*/
            
        }
        return billableAmount;
    }
}