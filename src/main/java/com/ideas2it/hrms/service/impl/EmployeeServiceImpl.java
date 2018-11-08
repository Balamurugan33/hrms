package com.ideas2it.hrms.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.hrms.dao.EmployeeDao;
import com.ideas2it.hrms.dao.impl.EmployeeDaoImpl;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Attendance;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.model.ProjectTask;
import com.ideas2it.hrms.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    
    /** {@inheritDoc}*/
    public Boolean createEmployee(Employee employee) throws AppException {
        return employeeDao.createEmployee(employee);

    }
    
    /** {@inheritDoc}*/
    public Boolean updateEmployee(Employee employee) throws AppException {
        return employeeDao.updateEmployee(employee);

    }
    
    /** {@inheritDoc}*/
    public List<Employee> displayEmployees() throws AppException {
        return employeeDao.retrieveEmployees();
    }
    
    /** {@inheritDoc}*/
    public Boolean deleteEmployee(Integer id) throws AppException {
        return employeeDao.deleteEmployee(id);
    }
    
    /** {@inheritDoc}*/
    public Boolean isEmployeeExist(String emailId) throws AppException {
        return (null == employeeDao.searchEmployee(emailId));
    }
    
    /** {@inheritDoc}*/
    public Employee searchEmployee(String emailId) throws AppException {
        return employeeDao.searchEmployee(emailId);
    }
    
    /** {@inheritDoc}*/
    public List<Project> getEmpProjects(List<ProjectTask> tasks) {
        List<Project> projects = new ArrayList<Project>();
        for(ProjectTask task:tasks) {
            if(! projects.contains(task.getProject())) {
                projects.add(task.getProject());
            }
        }
        return projects;
    }
    
    /** {@inheritDoc}*/
    public Integer calculateNetProfit(Employee employee) {
        List<ProjectTask> tasks = new ArrayList<ProjectTask>();
        Integer totalHoursWorked = 0;
        for(ProjectTask task : employee.getProjectTasks()) {
            if(task.getTaskDate().getMonth() == LocalDate.now().getMonth() && 
                    LocalDate.now().getYear() == task.getTaskDate().getYear()) {
                tasks.add(task);
            }
        }
        for(ProjectTask task : tasks) {
            Duration duration 
                = Duration.between(task.getEndTime(), task.getStartTime());
            totalHoursWorked = totalHoursWorked + (int) duration.toHours();
        }
        Integer billableAmount 
            = totalHoursWorked * employee.getDesignation().getHourlyRate();
        Integer salary = employee.getDesignation().getSalary();
        return (billableAmount - salary) - 
            (getEmpLeaves(employee).size()*(salary/30));
    }
    
    /** {@inheritDoc}*/
    public List<Attendance> getEmpLeaves(Employee employee) {
        List<Attendance> leaves = new ArrayList<Attendance>();
        for(Attendance attendance : employee.getAttendance()) {
            if(attendance.getDate().getMonth() == LocalDate.now().getMonth() && 
                    LocalDate.now().getYear() == attendance.getDate().getYear() 
                    && !attendance.getStatus()) {
                leaves.add(attendance);
            }
        }
        return leaves;
    }
}