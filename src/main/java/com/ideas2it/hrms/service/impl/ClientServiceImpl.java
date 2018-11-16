package com.ideas2it.hrms.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.hrms.dao.ClientDao;
import com.ideas2it.hrms.dao.impl.ClientDaoImpl;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Client;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.service.ClientService;

public class ClientServiceImpl implements ClientService {
    
    private ClientDao clientDao = new ClientDaoImpl();
    
    /** {@inheritDoc}*/
    public Boolean createClient(Client client) throws AppException {
        return clientDao.createClient(client);
    }
    
    public Client getClientById(Integer id) throws AppException {
        return clientDao.getClientById(id);
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
    public Client searchClient(String email) throws AppException {
        return clientDao.searchClient(email);
    }
    
    public Integer calculateNetProfit(Client client, LocalDate startDate, LocalDate endDate) throws AppException {
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        List<Project> clientProjects = client.getProjects();
        Integer netProfit = 0;
        
        for (Project project: clientProjects) {
            netProfit = netProfit + projectService.calculateNetProfit(project, startDate, endDate); 
        }
        return netProfit;
    }
    
    public ArrayList getClientNames() throws AppException {
        ArrayList names = new ArrayList();
        for (Client client : clientDao.retrieveClients()) {
            names.add(client.getName());
        }
        return names;
    }
    
    public ArrayList getClientProfits() throws AppException {
        ArrayList profits = new ArrayList();
        for (Client client : clientDao.retrieveClients()) {
            profits.add(calculateNetProfit(client, LocalDate.parse("2018-11-09"), LocalDate.now()));
        }
        return profits;
    }
}