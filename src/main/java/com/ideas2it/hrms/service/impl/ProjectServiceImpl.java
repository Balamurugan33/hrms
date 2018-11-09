package com.ideas2it.hrms.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.hrms.dao.ProjectDao;
import com.ideas2it.hrms.dao.impl.ProjectDaoImpl;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.model.ProjectTask;
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
        ProjectTaskServiceImpl taskService = new ProjectTaskServiceImpl();
        List<ProjectTask> projectTasks = project.getProjectTasks();  
        List<ProjectTask> curMonthTasks = new ArrayList<ProjectTask>();
        Integer billableAmount = 0;

        curMonthTasks = taskService.getCurrentMonthTasks(projectTasks);
        billableAmount = calculateBillAllTasks(curMonthTasks);
                
        return billableAmount;
    }
    
    public Integer calculateBillAllTasks(List<ProjectTask> curMonthTasks) {
        ProjectTaskServiceImpl taskService = new ProjectTaskServiceImpl();
        Integer billAllTasks = 0;
        
        for (ProjectTask task: curMonthTasks) {
            Integer hourlyRate = task.getEmployee().getDesignation().getHourlyRate();            
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
        List<ProjectTask> projectTasks = project.getProjectTasks();  
        List<Employee> projectEmployees = new ArrayList<Employee>();
        Integer costToCompany = 0;

        // Get the list of unique employees working on this project
        for (ProjectTask task: projectTasks) {
            if (!projectEmployees.contains(task.getEmployee())) {
                projectEmployees.add(task.getEmployee());
            }
        }
        
        for (Employee employee: projectEmployees) {
            costToCompany = costToCompany + empService.calculateCostToCompany(employee);
        }
        
        return costToCompany;
    }
}
