package com.ideas2it.hrms.service.impl;

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
        
    public Integer calculateNetProfit(Project project) {
        Integer billableAmount = 0;
        Integer costToCompany = 0;
        Integer netProfit = 0;
        
        billableAmount = calculateBillableAmount(project);            
        costToCompany = calculateCostToCompany(project);
        
        netProfit = billableAmount - costToCompany;

        return netProfit;
    }
    
    public Integer calculateBillableAmount(Project project) {
        TimeSheetServiceImpl taskService = new TimeSheetServiceImpl();
        List<TimeSheet> projectTasks = project.getTimeSheet();  
        List<TimeSheet> curMonthTasks = new ArrayList<TimeSheet>();
        Integer billableAmount = 0;

        curMonthTasks = taskService.getCurrentMonthTasks(projectTasks);
        billableAmount = calculateBillAllTasks(curMonthTasks);
                
        return billableAmount;
    }
    
    public Integer calculateBillAllTasks(List<TimeSheet> curMonthTasks) {
        TimeSheetServiceImpl taskService = new TimeSheetServiceImpl();
        Integer billAllTasks = 0;
        
        for (TimeSheet task: curMonthTasks) {
            Integer hourlyRate = task.getEmployee().getHourlyRate();            
            Integer numHoursWorkedTask = 0;
            Integer taskBill = 0;

            numHoursWorkedTask = taskService.calculateTaskDuration(task);
            taskBill = hourlyRate * numHoursWorkedTask;
            billAllTasks = billAllTasks + taskBill;
        }        

        return billAllTasks;
    }
    
    public Integer calculateCostToCompany(Project project) {
        EmployeeServiceImpl empService = new EmployeeServiceImpl();
        List<TimeSheet> projectTasks = project.getTimeSheet();  
        List<Employee> projectEmployees = new ArrayList<Employee>();
        Integer costToCompany = 0;

        // Get the list of unique employees working on this project
        for (TimeSheet task: projectTasks) {
            if (!projectEmployees.contains(task.getEmployee())) {
                projectEmployees.add(task.getEmployee());
            }
        }
        
        for (Employee employee: projectEmployees) {
            costToCompany = costToCompany + empService.calculateCostToCompany(employee);
        }
        
        return costToCompany;
    }
    
    /** {@inheritDoc}*/
    public List<Client> displayClients() throws AppException {
        ClientService clientService = new ClientServiceImpl();
        return clientService.displayClients();
    }
}
