package com.ideas2it.hrms.service.impl;

import java.time.Duration;
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
    
    public Integer calculateBudget(Project project) {
        List<ProjectTask> projectTasks = project.getProjectTasks();
        List<Employee> projectEmployees = new ArrayList<Employee>();
        Integer budget = 0;
        
        // Get the list of unique employees working on this project
        for(ProjectTask task: projectTasks) {
            if(!projectEmployees.contains(task.getEmployee())) {
                projectEmployees.add(task.getEmployee());
            }
        }
        // Iterate through the list of employees working on this project
        for(Employee employee: projectEmployees) {
            // Calculate the billable amount for a single employee
            budget = budget + calculateBillableAmount(project, employee);
        }
        
        return budget;
    }
    
    public Integer calculateBillableAmount(Project project, Employee employee) {
        List<ProjectTask> empTasks = employee.getProjectTasks();
        List<ProjectTask> projectTasks = new ArrayList<ProjectTask>();
        Integer billableAmount = 0;
        Integer numHoursWorkedTask = 0;
        Integer totalHoursWorked = 0;
        
        // Get the list of tasks done by employee for this project
        for(ProjectTask task: empTasks) {
            if(task.getProject() == project) {
                projectTasks.add(task);
            }
        }
        // Iterate through the list of tasks done by employee for this project
        for(ProjectTask task: projectTasks) {
            Duration duration 
                = Duration.between(task.getEndTime(), task.getStartTime());
            numHoursWorkedTask = (int) duration.toHours();
            totalHoursWorked = totalHoursWorked + numHoursWorkedTask;
        }        
        billableAmount 
            = totalHoursWorked * employee.getDesignation().getHourlyRate();
        
        return billableAmount;
    }
}
