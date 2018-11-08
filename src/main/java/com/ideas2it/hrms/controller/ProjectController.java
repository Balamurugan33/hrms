package com.ideas2it.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.service.ProjectService;
import com.ideas2it.hrms.service.impl.ProjectServiceImpl;

import static com.ideas2it.hrms.common.ProjectConstants.MSG_CREATED;
import static com.ideas2it.hrms.common.ProjectConstants.MSG_DELETED;
import static com.ideas2it.hrms.common.ProjectConstants.MSG_UPDATED;


/**
 * Provides functionality to manage projects delegated to the company
 * 
 * @author Ganesh Venkat S
 */
@Controller 
@RequestMapping("project") 
public class ProjectController {
            
    @PostMapping("create")
    public ModelAndView createProject(
            @ModelAttribute("project") Project project, 
            HttpServletRequest request) {
        ProjectService projectService = new ProjectServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            // Check if duplicate project exists including deleted projects
            project = projectService.createProject(project);   
            modelAndView.addObject("Success", MSG_CREATED);
            // redirect him to the same page; the projects must also be sent
            // alert box is optional for now
            modelAndView.setViewName("projects");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
       return modelAndView;
    }
    
    @PostMapping("update")
    public ModelAndView updateProject(
        @ModelAttribute("project") Project project, 
        HttpServletRequest request) {
        ProjectService projectService = new ProjectServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            // Check if the project to be updated exists 
            project = projectService.updateProject(project);   
            // redirect him to the same page; the projects must also be sent
            // alert box is optional for now
            modelAndView.setViewName("projects");
        } catch (AppException appException) {
            modelAndView.addObject("Error", MSG_UPDATED);
        }
        return modelAndView;
    }
        
    @PostMapping("delete")
    public ModelAndView deleteProject(HttpServletRequest request) {
        ProjectService projectService = new ProjectServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            // Check if the project to be deleted exists 
            Integer id = Integer.parseInt(request.getParameter("id"));
            Project project = projectService.getProjectById(id);
            if (null != project) {
                project = projectService.removeProject(project);    
            }
            modelAndView.addObject("Success", MSG_DELETED);
            // redirect him to the same page; the projects must also be sent
            // alert box is optional for now
            modelAndView.setViewName("projects");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());  
        }
        return modelAndView;
    }
    
    // Check if this method is redundant
    @GetMapping("displayAll")
    public ModelAndView displayAllProjects(HttpServletRequest request) {
        ProjectService projectService = new ProjectServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            List<Project> allProjects = projectService.getAllProjects();
            modelAndView.addObject("allProjects", allProjects);
            // redirect him to the same page; the projects must also be sent
            // alert box is optional for now
            modelAndView.setViewName("projects");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
        return modelAndView;
    }
}