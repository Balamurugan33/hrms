package com.ideas2it.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.model.ProjectTask;
import com.ideas2it.hrms.service.ProjectTaskService;
import com.ideas2it.hrms.service.impl.ProjectTaskServiceImpl;

import static com.ideas2it.hrms.common.ProjectConstants.MSG_CREATED;
import static com.ideas2it.hrms.common.ProjectConstants.MSG_DELETED;
import static com.ideas2it.hrms.common.ProjectConstants.MSG_UPDATED;

/**
 * Provides functionality to manage tasks of projects
 * 
 * @author Ganesh Venkat S
 */
@Controller 
public class ProjectTaskController {
            
    @PostMapping("projectTask/create")
    public ModelAndView createTask(@ModelAttribute("task") ProjectTask task, 
            HttpServletRequest request) {
        ProjectTaskService taskService = new ProjectTaskServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            // Check if duplicate task exists including deleted tasks
            Project project = new Project();
            project.setId(Integer.parseInt(request.getParameter("projectId")));
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(request.getParameter("empId")));
            task.setProject(project);
            task.setEmployee(employee);
            task = taskService.createTask(task);   
            modelAndView.addObject("Success", MSG_CREATED);
            // redirect him to the same page; the tasks must also be sent
            // alert box is optional for now
            modelAndView.setViewName("tasks");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
       return modelAndView;
    }
    
    @PostMapping("update")
    public ModelAndView updateTask(
        @ModelAttribute("task") ProjectTask task, 
        HttpServletRequest request) {
        ProjectTaskService taskService = new ProjectTaskServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            // Check if the task to be updated exists 
            task = taskService.updateTask(task);   
            modelAndView.addObject("Success", MSG_UPDATED);
            // redirect him to the same page; the tasks must also be sent
            // alert box is optional for now
            modelAndView.setViewName("tasks");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());  
        }
        return modelAndView;
    }
        
    @PostMapping("delete")
    public ModelAndView deleteTask(HttpServletRequest request) {
        ProjectTaskService taskService = new ProjectTaskServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            // Check if the task to be deleted exists 
            Integer id = Integer.parseInt(request.getParameter("id"));
            ProjectTask task = taskService.getTaskById(id);
            if (null != task) {
                task = taskService.removeTask(task);    
            }
            modelAndView.addObject("Success", MSG_DELETED);
            // redirect him to the same page; the tasks must also be sent
            // alert box is optional for now
            modelAndView.setViewName("tasks");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());  
        }
        return modelAndView;
    }
    
    // Check if this method is redundant
    @GetMapping("displayAll")
    public ModelAndView displayAllTasks(HttpServletRequest request) {
        ProjectTaskService taskService = new ProjectTaskServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            List<ProjectTask> allTasks = taskService.getAllTasks();
            modelAndView.addObject("allTasks", allTasks);
            // redirect him to the same page; the tasks must also be sent
            // alert box is optional for now
            modelAndView.setViewName("tasks");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
        return modelAndView;
    }
}