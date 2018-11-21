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
import com.ideas2it.hrms.service.SalaryTrackerService;

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
    
    public Integer calculateCompanyRevenue(LocalDate startDate, LocalDate endDate) throws AppException {
        Integer companyRevenue = 0;
        List<Client> clients = displayClients();
                
        for (Client client: clients) {
            companyRevenue = companyRevenue + calculateBillableAmount(client, startDate, endDate);
        }
        return companyRevenue;
    }
    
    public Integer calculateCompanyExpenditure(LocalDate startDate, LocalDate endDate) throws AppException {
        Integer companyExpenditure = 0;
        List<Client> clients = displayClients();
        
        for (Client client: clients) {
            companyExpenditure = companyExpenditure + calculateCostToCompany(client, startDate, endDate);
        }
        return companyExpenditure;
    }
    
    public Integer calculateCompanyNetProfit(LocalDate startDate, LocalDate endDate) throws AppException {
        Integer companyNetProfit = 0;
        List<Client> clients = displayClients();

        for (Client client: clients) {
            companyNetProfit = companyNetProfit + calculateNetProfit(client, startDate, endDate);    
        }        
        return companyNetProfit;
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
    
    public ArrayList<String> getClientNames() throws AppException {
        ArrayList<String> names = new ArrayList<String>();
        for (Client client : clientDao.retrieveClients()) {
            names.add(client.getName());
        }
        return names;
    }
    
    public ArrayList<Integer> getClientProfits() throws AppException {
        SalaryTrackerService salaryService = new SalaryTrackerServiceImpl();
        ArrayList<Integer> profits = new ArrayList<Integer>();
        LocalDate startDate 
            = salaryService.displaySalaryTrackers().get(0).getUpdateDate();
        for (Client client : clientDao.retrieveClients()) {
            profits.add(calculateNetProfit(client, startDate, LocalDate.now()));
        }
        return profits;
    }    
    
    public Integer calculateBillableAmount(Client client, LocalDate startDate, LocalDate endDate) throws AppException {
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        List<Project> clientProjects = client.getProjects();
        Integer billableAmount = 0;
        
        for (Project project: clientProjects) {
            billableAmount = billableAmount + projectService.calculateBillableAmount(project, startDate, endDate); 
        }        
        return billableAmount;                
    }
    
    public Integer calculateCostToCompany(Client client, LocalDate startDate, LocalDate endDate) throws AppException {
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        List<Project> clientProjects = client.getProjects();
        Integer costToCompany = 0;
        
        for (Project project: clientProjects) {
            costToCompany = costToCompany + projectService.calculateBillableAmount(project, startDate, endDate); 
        }        
        return costToCompany;                
    }
}