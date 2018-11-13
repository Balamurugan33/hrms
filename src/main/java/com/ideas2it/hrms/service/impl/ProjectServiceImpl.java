package com.ideas2it.hrms.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.hrms.dao.ProjectDao;
import com.ideas2it.hrms.dao.impl.ProjectDaoImpl;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Client;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.model.TimeSheet;
import com.ideas2it.hrms.service.ClientService;
import com.ideas2it.hrms.service.ProjectService;

/**
 * <p>
 * Implements ProjectService interface
 * </p>
 *
 * @author Ganesh Venkat S
 */
public class ProjectServiceImpl implements ProjectService {
    
    public Project createProject(Project project) throws AppException {
        ProjectDao projectDao = new ProjectDaoImpl();
        return projectDao.createProject(project);
    }
    
    public Project getProjectById(Integer id) throws AppException {
        ProjectDao projectDao = new ProjectDaoImpl();
        return projectDao.getProjectById(id);
    }
    
    public List<Project> getAllProjects() throws AppException {
        ProjectDao projectDao = new ProjectDaoImpl();
        return projectDao.getAllProjects();
    }
    
    public Project updateProject(Project project) throws AppException {
        ProjectDao projectDao = new ProjectDaoImpl();
        return projectDao.updateProject(project);
    }

    public Project removeProject(Project project) throws AppException {
        ProjectDao projectDao = new ProjectDaoImpl();
        return projectDao.removeProject(project);
    }
        
    public Integer calculateNetProfit(Project project, LocalDate startDate, LocalDate endDate) {
        Integer billableAmount = 0;
        Integer costToCompany = 0;
        Integer netProfit = 0;
        
        billableAmount = calculateBillableAmount(project, startDate, endDate);            
        costToCompany = calculateCostToCompany(project, startDate, endDate);
        
        netProfit = billableAmount - costToCompany;

        return netProfit;
    }
    
    public Integer calculateBillableAmount(Project project, LocalDate startDate, LocalDate endDate) {
        TimeSheetServiceImpl sheetService = new TimeSheetServiceImpl();
        List<TimeSheet> projectTimeSheet = project.getTimeSheet();  
        List<TimeSheet> timeSheetEntries = new ArrayList<TimeSheet>();
        Integer billableAmount = 0;

        timeSheetEntries = sheetService.getTimeSheetEntries(projectTimeSheet, startDate, endDate);
        billableAmount = calculateTotalBill(timeSheetEntries);
                
        return billableAmount;
    }
    
    public Integer calculateTotalBill(List<TimeSheet> timeSheetEntries) {
        TimeSheetServiceImpl sheetService = new TimeSheetServiceImpl();
        Integer totalBill = 0;
        
        for (TimeSheet entry: timeSheetEntries) {
            //Integer hourlyRate = entry.getEmployee().getSalaryTracker().getHourlyRate();            
            Integer numHoursWorkedEntry = 0;
            Integer entryBill = 0;

           // numHoursWorkedEntry = entry.getBillableHours();
           // entryBill = hourlyRate * numHoursWorkedEntry;
            totalBill = totalBill + entryBill;
        }        

        return totalBill;
    }
    
    public Integer calculateCostToCompany(Project project, LocalDate startDate, LocalDate endDate) {
        EmployeeServiceImpl empService = new EmployeeServiceImpl();
        List<TimeSheet> projectTimeSheet = project.getTimeSheet();  
        List<Employee> projectEmployees = new ArrayList<Employee>();
        Integer costToCompany = 0;

        // Get the list of unique employees working on this project
        for (TimeSheet entry: projectTimeSheet) {
            if (!projectEmployees.contains(entry.getEmployee())) {
                projectEmployees.add(entry.getEmployee());
            }
        }
        
        for (Employee employee: projectEmployees) {
           // costToCompany = costToCompany + empService.calculateCostToCompany(employee, startDate, endDate);
        }
        
        return costToCompany;
    }
    
    /** {@inheritDoc}*/
    public List<Client> displayClients() throws AppException {
        ClientService clientService = new ClientServiceImpl();
        return clientService.displayClients();
    }

    @Override
    public Integer calculateBillAllTasks(List<TimeSheet> curMonthTasks) {
        // TODO Auto-generated method stub
        return null;
    }
}
