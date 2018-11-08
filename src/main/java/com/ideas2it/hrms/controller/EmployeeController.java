package com.ideas2it.hrms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.hrms.common.EmpConstants;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.service.EmployeeService;
import com.ideas2it.hrms.service.impl.EmployeeServiceImpl;

/**
 * Used to perform the different action on employee 
 * using employee service class 
 * 
 * @version 1
 * @author Balamurugan M
 */
public class EmployeeController {
    
    private String EMPLOYEE_MENU = "employeeView";
    private String ADMINMENU = "adminMenu";
    private String ERROR_PAGE = "error";
    private EmployeeService employeeService = new EmployeeServiceImpl();
    
    @GetMapping("/viewProfile")
    public String viewProfile() {
        return EMPLOYEE_MENU;
    }
    
    @PostMapping("/createEmployee")
    public ModelAndView createEmployee(@ModelAttribute("employee") 
            Employee employee) {
        ModelMap model = null;
        try {
            if(!employeeService.isEmployeeExist(employee.getEmailId())) {
                if (employeeService.createEmployee(employee)) {
                    model.addAttribute(EmpConstants.LABEL_MESSAGE,
                        EmpConstants.MSG_CREATE_SUCCESS);
                } else {
                    model.addAttribute(EmpConstants.LABEL_MESSAGE, 
                        EmpConstants.MSG_CREATE_FAIL);
                }
            }
            model.addAttribute(EmpConstants.LABEL_MESSAGE, 
                EmpConstants.MSG_ALREADY_EXIST);
            return displayEmployees(model);
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE, 
                 appException.getMessage());
        }
    }
    
    @PostMapping("/updateEmployee")
    public ModelAndView updateEmployee(@ModelAttribute("employee") 
            Employee employee) {
        ModelMap model = null;
        try {
            if (employeeService.updateEmployee(employee)) {
                model.addAttribute(EmpConstants.LABEL_MESSAGE,
                    EmpConstants.MSG_UPDATE_SUCCESS);
            } else {
                model.addAttribute(EmpConstants.LABEL_MESSAGE, 
                    EmpConstants.MSG_UPDATE_FAIL);
            }
            return displayEmployees(model);
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE, 
                 appException.getMessage());
        }
    }
    
    @PostMapping("/deleteEmployee")
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
    
    @GetMapping("/displayEmployee")
    public ModelAndView displayEmployees(ModelMap model) {
        try {
            return new ModelAndView(EMPLOYEE_MENU, EmpConstants.LABEL_EMPLOYEES, 
                employeeService.displayEmployees());
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE, 
                appException.getMessage());
        }
    }
    
    @GetMapping("/empProjects")
    public ModelAndView employeesProjects(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Employee employee = (Employee) session.getAttribute("employee");
        return new ModelAndView(EMPLOYEE_MENU, EmpConstants.LABEL_PROJECTS, 
            employeeService.getEmpProjects(employee.getProjectTask()));
    }
}