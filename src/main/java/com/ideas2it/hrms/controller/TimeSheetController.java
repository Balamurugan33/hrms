package com.ideas2it.hrms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.model.TimeSheet;
import com.ideas2it.hrms.service.ProjectService;
import com.ideas2it.hrms.service.TimeSheetService;
import com.ideas2it.hrms.service.impl.ProjectServiceImpl;
import com.ideas2it.hrms.service.impl.TimeSheetServiceImpl;

import static com.ideas2it.hrms.common.ProjectConstants.MSG_CREATED;
import static com.ideas2it.hrms.common.ProjectConstants.MSG_DELETED;
import static com.ideas2it.hrms.common.ProjectConstants.MSG_UPDATED;

/**
 * Provides functionality to manage employee TimeSheets
 * 
 * @author Ganesh Venkat S
 */
@Controller 
public class TimeSheetController {       
    @PostMapping("timeSheet/createEntry")
    public ModelAndView createEntry(@ModelAttribute("entry") TimeSheet entry, 
            HttpServletRequest request) {
        TimeSheetService sheetService = new TimeSheetServiceImpl();
        ModelAndView modelAndView = new ModelAndView();                
        HttpSession session = request.getSession();        
        Employee employee = (Employee) session.getAttribute("employee");
        List<TimeSheet> timeSheet = new ArrayList<TimeSheet>();
                
        try {
            Project project = new Project();
            project.setId(Integer.parseInt(request.getParameter("projectId")));
            entry.setProject(project);
            entry.setEmployee(employee);
            entry = sheetService.createTask(entry);       
            timeSheet = sheetService.getAllTasks();
            modelAndView.addObject("timeSheets", timeSheet);
            modelAndView.addObject("Success", MSG_CREATED);
            modelAndView.setViewName("employeeView");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
        return modelAndView;
    } 
    
    @PostMapping("projectTask/create")
    public ModelAndView createTask(@ModelAttribute("task") TimeSheet task, 
            HttpServletRequest request) {
        TimeSheetService taskService = new TimeSheetServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            Project project = new Project();
            project.setId(Integer.parseInt(request.getParameter("projectId")));
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(request.getParameter("empId")));
            task.setProject(project);
            task.setEmployee(employee);
            task = taskService.createTask(task);   
            modelAndView.addObject("Success", MSG_CREATED);
            modelAndView.setViewName("tasks");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
       return modelAndView;
    }
    
    @PostMapping("projectTask/update")
    public ModelAndView updateTask(@ModelAttribute("task") TimeSheet task, HttpServletRequest request) {
        TimeSheetService sheetService = new TimeSheetServiceImpl();
        ProjectService projectService = new ProjectServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            AppLogger.error("bhgih"+task.getEntryDate());
            List<TimeSheet> timeSheet = new ArrayList<TimeSheet>();
            HttpSession session = request.getSession();
            Employee currentEmployee = (Employee) session.getAttribute("employee");
            Integer projectId = Integer.parseInt(request.getParameter("projectId"));
            String projectName = request.getParameter("projectName");            
            
            Project project = projectService.getProjectById(projectId);
            task.setProject(project);
            task.setEmployee(currentEmployee);
            task.getProject().setName(projectName);
            task = sheetService.updateTask(task);   
            timeSheet = sheetService.getAllTasks();

            modelAndView.addObject("Success", MSG_UPDATED);
            modelAndView.addObject("timeSheets", timeSheet);
            modelAndView.setViewName("employeeView");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());  
        }
        return modelAndView;
    }
        
    @PostMapping("projectTask/delete")
    public ModelAndView deleteTask(HttpServletRequest request) {
        TimeSheetService sheetService = new TimeSheetServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 
        List<TimeSheet> timeSheet = new ArrayList<TimeSheet>();

        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            TimeSheet task = sheetService.getTaskById(id);
            
            if (null != task) {
                task = sheetService.removeTask(task);    
                timeSheet = sheetService.getAllTasks();
            }            
            modelAndView.addObject("Success", MSG_DELETED);
            modelAndView.addObject("timeSheets", timeSheet);
            modelAndView.setViewName("employeeView");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());  
        }
        return modelAndView;
    }
    
    @GetMapping("displayAll")
    public ModelAndView displayAllTasks(HttpServletRequest request) {
        TimeSheetService taskService = new TimeSheetServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            List<TimeSheet> allTasks = taskService.getAllTasks();
            modelAndView.addObject("allTasks", allTasks);
            modelAndView.setViewName("tasks");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
        return modelAndView;
    }
}