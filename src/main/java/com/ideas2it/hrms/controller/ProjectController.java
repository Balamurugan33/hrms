package com.ideas2it.hrms.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Client;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.service.ProjectService;
import com.ideas2it.hrms.service.impl.ProjectServiceImpl;

import static com.ideas2it.hrms.common.ProjectConstants.MSG_CREATED;
import static com.ideas2it.hrms.common.ProjectConstants.MSG_DELETED;
import static com.ideas2it.hrms.common.ProjectConstants.MSG_UPDATED;
import static com.ideas2it.hrms.common.ProjectConstants.USER_ALERT;



/**
 * Provides functionality to manage projects delegated to the company
 * And calculate netProfit for company from a project, between a startDate and an endDate
 * 
 * @author Ganesh Venkat S
 */
@Controller 
@RequestMapping("project") 
public class ProjectController {
    
    /**
     * Creates a new project
     */
    @PostMapping("create")
    public ModelAndView createProject(
            @ModelAttribute("project") Project project, 
            HttpServletRequest request) {
        ProjectService projectService = new ProjectServiceImpl();
        ModelAndView modelAndView = new ModelAndView("redirect:"+"displayAll");
        HttpSession session = request.getSession(false);
        
        try {
            Client client = new Client();
            client.setId(Integer.parseInt(request.getParameter("clientId")));
            project.setClient(client);
            project = projectService.createProject(project);   
            session.setAttribute(USER_ALERT, MSG_CREATED);
        } catch (AppException appException) {
            session.setAttribute(USER_ALERT, appException.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * Updates a project
     */
    @PostMapping("update")
    public ModelAndView updateProject(
        @ModelAttribute("project") Project project, 
        HttpServletRequest request) {
        ProjectService projectService = new ProjectServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        modelAndView.setViewName("projects");
        try {
            Client client = new Client();
            client.setId(Integer.parseInt(request.getParameter("clientId")));
            project.setClient(client);
            project = projectService.updateProject(project);   
        } catch (AppException appException) {
            modelAndView.addObject(USER_ALERT, MSG_UPDATED);
        }
        return displayAllProjects(modelAndView);
    }
     
    /**
     * Deletes a project
     */
    @PostMapping("delete")
    public ModelAndView deleteProject(HttpServletRequest request) {
        ProjectService projectService = new ProjectServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Project project = projectService.getProjectById(id);
            if (null != project) {
                project = projectService.removeProject(project);    
            }
            modelAndView.addObject("Success", MSG_DELETED);
            modelAndView.setViewName("projects");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());  
        }
        return displayAllProjects(modelAndView);
    }
    
    /**
     * Displays all projects
     */
    @GetMapping("displayAll")
    public ModelAndView displayAllProjects(ModelAndView modelAndView) {
        ProjectService projectService = new ProjectServiceImpl();

        try {
            modelAndView.addObject("allClients", 
                    projectService.getAllClients());
            List<Project> allProjects = projectService.getAllProjects();
            modelAndView.addObject("projects", allProjects);
            modelAndView.setViewName("adminHome");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * Gets the netProfit from a project, within a time interval
     */
    @PostMapping("netProfit")
    public ModelAndView getNetProfit(HttpServletRequest request) {
        ProjectService projectService = new ProjectServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            Integer projectId = Integer.parseInt(request.getParameter("projectId"));
            LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
            LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));            
            Integer netProfit;
            Integer billableAmount;
            Integer costToCompany;
            Project project;
            Client client = new Client();
            
            project = projectService.getProjectById(projectId);
            netProfit = projectService.calculateNetProfit(project, startDate, endDate);
            billableAmount = projectService.calculateBillableAmount(project, startDate, endDate);
            costToCompany = projectService.calculateCostToCompany(project, startDate, endDate);
            modelAndView.addObject("Profit", netProfit);
            modelAndView.addObject("BilAmount", billableAmount);
            modelAndView.addObject("CostToCompany", costToCompany);
            modelAndView.addObject("Success", MSG_CREATED);
            return displayAllProjects(modelAndView);
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
            return modelAndView;
        }
    }
}