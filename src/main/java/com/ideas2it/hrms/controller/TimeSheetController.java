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
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.model.TimeSheet;
import com.ideas2it.hrms.service.ProjectService;
import com.ideas2it.hrms.service.TimeSheetService;
import com.ideas2it.hrms.service.impl.ProjectServiceImpl;
import com.ideas2it.hrms.service.impl.TimeSheetServiceImpl;

import static com.ideas2it.hrms.common.ProjectConstants.MSG_DELETED;
import static com.ideas2it.hrms.common.ProjectConstants.MSG_UPDATED;
import static com.ideas2it.hrms.common.ProjectConstants.USER_ALERT;

/**
 * Provides functionality to manage employee TimeSheets
 * 
 * @author Ganesh Venkat S
 */
@Controller 
public class TimeSheetController {       
    
    /**
     * Creates a new timesheet entry
     */
    @PostMapping("timeSheet/createEntry")
    public ModelAndView createEntry(@ModelAttribute("entry") TimeSheet entry, 
            HttpServletRequest request) {
        TimeSheetService sheetService = new TimeSheetServiceImpl();
        ModelAndView modelAndView = new ModelAndView();                
        HttpSession session = request.getSession();        
        Employee currentEmployee = (Employee) session.getAttribute("employee");
        List<TimeSheet> timeSheet = new ArrayList<TimeSheet>();
                
        try {
            Project project = new Project();
            project.setId(Integer.parseInt(request.getParameter("projectId")));
            entry.setProject(project);
            entry.setEmployee(currentEmployee);
            entry = sheetService.createEntry(entry);       
            Employee employee
                = sheetService.searchEmployee(request.getParameter("empEmail"));
            session.setAttribute("employee", employee);
            modelAndView.setViewName("redirect:"+"/timeSheet/viewProfile");
        } catch (AppException appException) {
            modelAndView.addObject(USER_ALERT, appException.getMessage());
        }
        return modelAndView;
    } 
    
    @GetMapping("timeSheet/viewProfile")
    public ModelAndView viewTimeSheet(HttpServletRequest request) {
        HttpSession session = request.getSession();        
        Employee employee = (Employee) session.getAttribute("employee");
        return new ModelAndView("employeeView", "timeSheets", 
            employee.getTimeSheet());
    }

    /**
     * Updates a timesheet entry
     */
    @PostMapping("timeSheet/updateEntry")
    public ModelAndView updateTask(@ModelAttribute("task") TimeSheet entry, HttpServletRequest request) {
        TimeSheetService sheetService = new TimeSheetServiceImpl();
        ProjectService projectService = new ProjectServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            List<TimeSheet> timeSheet = new ArrayList<TimeSheet>();
            HttpSession session = request.getSession();
            Employee currentEmployee = (Employee) session.getAttribute("employee");
            Integer projectId = Integer.parseInt(request.getParameter("projectId"));
            Project project = projectService.getProjectById(projectId);
            entry.setProject(project);
            entry.setEmployee(currentEmployee);
            entry = sheetService.updateEntry(entry);
            Employee employee
                = sheetService.searchEmployee(request.getParameter("empEmail"));
            timeSheet = employee.getTimeSheet();

            modelAndView.addObject(USER_ALERT, MSG_UPDATED);
            modelAndView.addObject("timeSheets", timeSheet);
            modelAndView.setViewName("employeeView");
        } catch (AppException appException) {
            modelAndView.addObject(USER_ALERT, appException.getMessage());  
        }
        return modelAndView;
    }
    
    /**
     * Deletes a timesheet entry
     */
    @PostMapping("timeSheet/deleteEntry")
    public ModelAndView deleteEntry(HttpServletRequest request) {
        TimeSheetService sheetService = new TimeSheetServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 
        List<TimeSheet> timeSheet = new ArrayList<TimeSheet>();

        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            TimeSheet entry = sheetService.getEntryById(id);
            
            if (null != entry) {
                entry = sheetService.removeEntry(entry);    
                timeSheet = sheetService.getAllEntries();
            }            
            modelAndView.addObject(USER_ALERT, MSG_DELETED);
            modelAndView.addObject("timeSheets", timeSheet);
            modelAndView.setViewName("employeeView");
        } catch (AppException appException) {
            modelAndView.addObject(USER_ALERT, appException.getMessage());  
        }
        return modelAndView;
    }
    
    /**
     * Displays all timesheet entries
     */
    @GetMapping("displayAll")
    public ModelAndView displayAllEntries(HttpServletRequest request) {
        TimeSheetService entryService = new TimeSheetServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            List<TimeSheet> allEntries = entryService.getAllEntries();
            modelAndView.addObject("allTasks", allEntries);
            modelAndView.setViewName("tasks");
        } catch (AppException appException) {
            modelAndView.addObject(USER_ALERT, appException.getMessage());
        }
        return modelAndView;
    }        
}