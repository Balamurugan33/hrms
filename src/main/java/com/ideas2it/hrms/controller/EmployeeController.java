package com.ideas2it.hrms.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.hrms.common.EmpConstants;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.model.Attendance;
import com.ideas2it.hrms.model.Designation;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.service.EmployeeService;
import com.ideas2it.hrms.service.impl.EmployeeServiceImpl;

import static com.ideas2it.hrms.common.EmpConstants.MSG_CREATE_SUCCESS;

/**
 * Used to perform the different action on employee 
 * using employee service class 
 * 
 * @version 1
 * @author Balamurugan M
 */
@Controller
public class EmployeeController {
    
    private String EMPLOYEE_MENU = "employeeView";
    private String EMPLOYEE_CREATE = "empCreate";
    private String ADMINMENU = "adminHome";
    private String ERROR_PAGE = "error";
    private EmployeeService employeeService = new EmployeeServiceImpl();
    
    @GetMapping("employee/viewProfile")
    public ModelAndView viewProfile(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Employee employee = (Employee) session.getAttribute("employee");
        return new ModelAndView(EMPLOYEE_MENU, 
            "employeeDetail", employee);
    }
    
    @GetMapping("employee/createProfile")
    public ModelAndView viewCreateForm() {
        try {
            ModelAndView modelAndView 
                = new ModelAndView(EMPLOYEE_CREATE, "command", new Employee());
            return modelAndView.addObject("designations", 
                employeeService.getDesignations());
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE, 
                appException.getMessage());
       }
    }
    
    @PostMapping("employee/createEmployee")
    public ModelAndView createEmployee(@ModelAttribute("employee") 
            Employee employee, HttpServletRequest request) {
        try {
            Integer id = Integer.parseInt(request.getParameter("designationId"));
            Designation designation = new Designation();
            designation.setId(id);
            employee.setDesignation(designation);
            if(employeeService.isEmployeeExist(employee.getEmailId())) {
                if (employeeService.createEmployee(employee)) {
                    return new ModelAndView(ADMINMENU, EmpConstants.
                        LABEL_MESSAGE, EmpConstants.MSG_CREATE_SUCCESS);
                } else {
                    return new ModelAndView(ADMINMENU, EmpConstants.
                        LABEL_MESSAGE, EmpConstants.MSG_CREATE_FAIL);
                }
            }
            return new ModelAndView(ADMINMENU, EmpConstants.
                    LABEL_MESSAGE, EmpConstants.MSG_ALREADY_EXIST);
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE, 
                 appException.getMessage());
        }
    }
    
    @GetMapping("/employee/markPresent")
    public ModelAndView markPresent(HttpServletRequest request) {
        HttpSession session = request.getSession();        
        Employee employee = (Employee) session.getAttribute("employee");
        List<Attendance> attendanceSheet = new ArrayList<Attendance>();
        ModelAndView modelAndView = new ModelAndView(); 
        
        try {
            // Check if already exists
            // If Exists do nothing, else create new attendance entry
            attendanceSheet = employeeService.markPresent(employee);   
            modelAndView.addObject("Success", MSG_CREATE_SUCCESS);
            modelAndView.addObject("attendance", attendanceSheet);
            modelAndView.addObject("isChecked", true);
            modelAndView.setViewName("employeeView");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
        
        return modelAndView;       
    }
    
    @GetMapping("/employee/markAbsent")
    public ModelAndView markAbsent(HttpServletRequest request) {
        HttpSession session = request.getSession();        
        Employee employee = (Employee) session.getAttribute("employee");
        // Check if there is an employee here
        // Flow is
        // Controller -> Attendance View -> Get request (w. session)
        // New URL Another get request (same request or not)
        List<Attendance> attendanceSheet = new ArrayList<Attendance>();
        ModelAndView modelAndView = new ModelAndView(); 
        
        try {
            attendanceSheet = employeeService.markAbsent(employee);   
            modelAndView.addObject("Success", MSG_CREATE_SUCCESS);
            modelAndView.addObject("attendance", attendanceSheet);
            modelAndView.addObject("isChecked", false);
            modelAndView.setViewName("employeeView");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
        
        return modelAndView;       
    }
    
    @PostMapping("employee/updateEmployee")
    public ModelAndView updateEmployee(@ModelAttribute("employee") 
            Employee employee, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            Employee oldEmployee = (Employee) session.getAttribute("employee");
            employee.setDesignation(oldEmployee.getDesignation());
            AppLogger.error("check3"+employee.getEmailId());
            if (employeeService.updateEmployee(employee)) {
                ModelAndView modelAndView = new ModelAndView(EMPLOYEE_MENU, 
                    EmpConstants.LABEL_MESSAGE, EmpConstants.MSG_UPDATE_SUCCESS);
                return modelAndView.addObject("employeeDetail", employee);
            } else {
                return new ModelAndView("createProfile", 
                    EmpConstants.LABEL_MESSAGE, EmpConstants.MSG_UPDATE_FAIL);
            }
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE, 
                 appException.getMessage());
        }
    }
    
    @PostMapping("employee/deleteEmployee")
    public ModelAndView deleteEmployee(HttpServletRequest request, 
            ModelMap model) {
        try {
            if (employeeService.deleteEmployee(Integer.parseInt(
                    request.getParameter("id")))) {
                model.addAttribute(EmpConstants.LABEL_MESSAGE, 
                    EmpConstants.MSG_DELETE_SUCCESS);
            } else {
                model.addAttribute(EmpConstants.LABEL_MESSAGE,
                    EmpConstants.MSG_DELETE_FAIL);
            }
            return displayEmployees(model);
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE, 
                 appException.getMessage());
        }
    }
    
    @GetMapping("employee/displayEmployee")
    public ModelAndView displayEmployees(ModelMap model) {
        try {
            return new ModelAndView(ADMINMENU, EmpConstants.LABEL_EMPLOYEES, 
                employeeService.displayEmployees());
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE, 
                appException.getMessage());
        }
    }
    
    @GetMapping("employee/empProjects")
    public ModelAndView employeesProjects(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Employee employee = (Employee) session.getAttribute("employee");
        return new ModelAndView(EMPLOYEE_MENU, EmpConstants.LABEL_PROJECTS, 
            employeeService.getEmpProjects(employee.getProjectTasks()));
    }
    
    @GetMapping("employee/empTasks")
    public ModelAndView employeesTasks(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Employee employee = (Employee) session.getAttribute("employee");
        return new ModelAndView(EMPLOYEE_MENU, EmpConstants.LABEL_TASKS, 
           employee.getProjectTasks());
    }
    
    @GetMapping("employee/empAttendance")
    public ModelAndView employeesAttendance(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Employee employee = (Employee) session.getAttribute("employee");
        return new ModelAndView(EMPLOYEE_MENU, EmpConstants.LABEL_ATTENDANCE, 
           employee.getAttendance());
    }
}