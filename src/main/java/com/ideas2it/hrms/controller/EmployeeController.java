package com.ideas2it.hrms.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.model.SalaryTracker;
import com.ideas2it.hrms.model.TimeSheet;
import com.ideas2it.hrms.service.EmployeeService;
import com.ideas2it.hrms.service.impl.EmployeeServiceImpl;


/**
 * Used to perform the different action on employee using employee service class
 * 
 * @version 1
 * @author Balamurugan M
 */
@Controller
public class EmployeeController {

    private String EMPLOYEE_MENU = "employeeView";
    private String ADMINMENU = "adminHome";
    private String ERROR_PAGE = "error";
    private EmployeeService employeeService = new EmployeeServiceImpl();
    
    /**
     * Used to view the employee menu page
     */
    @GetMapping("employee/viewProfile")
    public ModelAndView viewProfile(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Employee employee = (Employee) session.getAttribute("employee");
        return new ModelAndView(EMPLOYEE_MENU, 
            "employeeDetail", employee);
    }
    
    /**
     * Used to create the employee  
     *  
     * @param employee
     *        Get the the employee details  
     * @param salaryTracker
     *        Used to get the employee salary detail
     */
    @PostMapping("employee/createEmployee")
    public ModelAndView createEmployee(
            @ModelAttribute("employee") Employee employee,
            @ModelAttribute("salaryTracker") SalaryTracker salaryTracker,
            HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(
                "redirect:" + "/employee/displayEmployee");
        try {
            HttpSession session = request.getSession(false);
            List<SalaryTracker> trackers = new ArrayList<SalaryTracker>();
            salaryTracker.setEmployee(employee);
            trackers.add(salaryTracker);
            Integer id = Integer
                    .parseInt(request.getParameter("designationId"));
            Designation designation = new Designation();
            designation.setId(id);
            employee.setDesignation(designation);
            employee.setSalaryTrackers(trackers);
            if (employeeService.isEmployeeExist(employee.getEmailId())) {
                if (employeeService.createEmployee(employee)) {
                    session.setAttribute(EmpConstants.LABEL_MESSAGE,
                            EmpConstants.MSG_CREATE_SUCCESS);
                } else {
                    session.setAttribute(EmpConstants.LABEL_MESSAGE,
                            EmpConstants.MSG_CREATE_FAIL);
                }
            } else {
                session.setAttribute(EmpConstants.LABEL_MESSAGE,
                        EmpConstants.MSG_ALREADY_EXIST);
            }
            return modelAndView;
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE,
                    appException.getMessage());
        }
    }
    
    /**
     * Marks an employee as present for current date
     */
    @GetMapping("/employee/markPresent")
    public ModelAndView markPresent(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        List<Attendance> attendanceSheet = new ArrayList<Attendance>();
        ModelAndView modelAndView = new ModelAndView();

        try {
            attendanceSheet = employeeService.markPresent(employee);
            modelAndView.addObject("attendance", attendanceSheet);
            modelAndView.addObject("isChecked", true);
            modelAndView.setViewName("employeeView");
        } catch (AppException appException) {
            modelAndView.addObject(EmpConstants.LABEL_MESSAGE,
                appException.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * Marks an employee as absent for current date
     */
    @GetMapping("/employee/markAbsent")
    public ModelAndView markAbsent(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        List<Attendance> attendanceSheet = new ArrayList<Attendance>();
        ModelAndView modelAndView = new ModelAndView();

        try {
            attendanceSheet = employeeService.markAbsent(employee);
            modelAndView.addObject("attendance", attendanceSheet);
            modelAndView.addObject("isChecked", false);
            modelAndView.setViewName("employeeView");
        } catch (AppException appException) {
            modelAndView.addObject(EmpConstants.LABEL_MESSAGE,
                appException.getMessage());
        }
        return modelAndView;
    }
    
    /**
     * Used to update the employee details
     * 
     * @param employee
     *        Get the employee detail
     */
    @PostMapping("employee/updateEmployee")
    public ModelAndView updateEmployee(
            @ModelAttribute("employee") Employee employee,
            HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            Employee oldEmployee = (Employee) session.getAttribute("employee");
            employee.setDesignation(oldEmployee.getDesignation());
            AppLogger.error("check3" + employee.getEmailId());
            if (employeeService.updateEmployee(employee)) {
                ModelAndView modelAndView = new ModelAndView(EMPLOYEE_MENU,
                        EmpConstants.LABEL_MESSAGE,
                        EmpConstants.MSG_UPDATE_SUCCESS);
                return modelAndView.addObject("employeeDetail", employee);
            } else {
                return new ModelAndView("createProfile",
                        EmpConstants.LABEL_MESSAGE,
                        EmpConstants.MSG_UPDATE_FAIL);
            }
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE,
                    appException.getMessage());
        }
    }
    
    /**
     * Used to remove the employee from company 
     */
    @PostMapping("employee/deleteEmployee")
    public ModelAndView deleteEmployee(HttpServletRequest request,
            ModelMap model) {
        ModelAndView modelAndView = new ModelAndView(
                "redirect:" + "/employee/displayEmployee");
        try {
            HttpSession session = request.getSession(false);
            session.setAttribute(EmpConstants.LABEL_MESSAGE, 
                    EmpConstants.MSG_DELETE_SUCCESS);
            if (employeeService.deleteEmployee(
                    Integer.parseInt(request.getParameter("id")))) {
                session.setAttribute(EmpConstants.LABEL_MESSAGE, 
                    EmpConstants.MSG_DELETE_SUCCESS);
            } else {
                session.setAttribute(EmpConstants.LABEL_MESSAGE,
                        EmpConstants.MSG_DELETE_FAIL);
            }
            return modelAndView;
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE,
                    appException.getMessage());
        }
    }
    
    /**
     * Used to display the all employee 
     */
    @GetMapping("employee/displayEmployee")
    public ModelAndView displayEmployees(ModelMap model) {
        try {
            ModelAndView modelAndView = new ModelAndView(ADMINMENU, "command",
                    new TimeSheet());
            modelAndView.addObject("command", new Employee());
            modelAndView.addObject("command", new SalaryTracker());
            modelAndView.addObject("allProjects",
                    employeeService.getAllProjects());
            modelAndView.addObject("allDesignation",
                    employeeService.getDesignations());
            return modelAndView.addObject(EmpConstants.LABEL_EMPLOYEES,
                    employeeService.displayEmployees());
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE,
                    appException.getMessage());
        }
    }
    
    /**
     * Gets the projects an employee has worked on
     */
    @GetMapping("employee/empProjects")
    public ModelAndView employeesProjects(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        ModelAndView modelAndView = new ModelAndView();

        Employee employee = (Employee) session.getAttribute("employee");
        if (employee.getProjects().isEmpty()) {
            modelAndView.addObject("projectEmpty","NO PROJECTS FOUND");
            System.out.println("projectEmpty");
        }
        modelAndView.addObject("currentProjects", employee.getProjects());
        modelAndView.addObject("projects",
            employeeService.getEmpProjects(employee.getTimeSheet()));
        modelAndView.setViewName(EMPLOYEE_MENU);

        return modelAndView;
    }
    
    /**
     * Get the employee time sheet details
     */
    @GetMapping("employee/empTimesheet")
    public ModelAndView employeesTimesheet(HttpServletRequest request,
            Model model) {
        HttpSession session = request.getSession(false);
        Employee employee = (Employee) session.getAttribute("employee");
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.addObject("command", new TimeSheet());
        modelAndView.addObject("empProjects", employee.getProjects());
        if (employee.getTimeSheet().isEmpty()) {
            modelAndView.addObject("timesheetEmpty","NO ENTRIES FOUND");
        }
        modelAndView.addObject(EmpConstants.LABEL_TIMESHEETS,
            employee.getTimeSheet());
        modelAndView.setViewName(EMPLOYEE_MENU);
        return modelAndView;
    }
    
    /**
     * Gets an employee's attendance history
     */
    @GetMapping("employee/empAttendance")
    public ModelAndView employeesAttendance(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Employee employee = (Employee) session.getAttribute("employee");
        ModelAndView modelAndView = new ModelAndView();
        if (employee.getAttendance().isEmpty()) {
            modelAndView.addObject("attendanEmpty"," PUT ATTENDANCE");
        }
        modelAndView.addObject(EmpConstants.LABEL_ATTENDANCE, 
                employee.getAttendance());
        modelAndView.setViewName(EMPLOYEE_MENU);        
        return modelAndView;        
    }
    
    
    /**
     * Used by an employee to send an email, applying for leave
     */
    @PostMapping("employee/applyLeave")
    public ModelAndView Leave(@ModelAttribute("attendance") Attendance attendance, 
        HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session1 = request.getSession(false);        
        Employee employee = (Employee) session1.getAttribute("employee");
                
        LocalDate leaveDate = LocalDate.parse(request.getParameter("leaveDate"));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
        String dateString = leaveDate.format(dateFormatter);

        String leaveTemplate = "Hi, I would like to apply for leave on " + dateString + " as ";
        String message = null;
        List<Attendance> attendanceSheet = new ArrayList<Attendance>();
                        
        message = leaveTemplate + request.getParameter("leaveReason");

        try {
            attendanceSheet = employeeService.applyLeave(employee, message, dateString);
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
        
        modelAndView.addObject("attendance", attendanceSheet);
        modelAndView.setViewName("employeeView");
        return modelAndView;
    }
    
    /**
     * Get the particular employee net profit 
     */
    @PostMapping("employee/profit")
    public ModelAndView calculateNetProfit(HttpServletRequest request,
            ModelMap model) {
        try {
            Employee employee = employeeService
                    .searchEmployee(request.getParameter("empEmailId"));
            LocalDate startDate = LocalDate
                    .parse(request.getParameter("startDate"));
            LocalDate endDate = LocalDate
                    .parse(request.getParameter("endDate"));
            Integer billAmount = employeeService.calculateBillAmount(startDate,
                    endDate, employee);
            Integer costToCompany = employeeService
                    .calculateCostToCompany(startDate, endDate, employee);
            Integer profit = employeeService.calculateNetProfit(startDate,
                    endDate, employee);
            model.addAttribute("BilAmount", billAmount);
            model.addAttribute("CostToCompany", costToCompany);
            model.addAttribute("Profit", profit);
            return displayEmployees(model);
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE,
                    appException.getMessage());
        }
    }
    
    /**
     * Get the employee new salary detail
     * And create new salary tracker for employee
     * 
     * @param salaryTracker
     *        get the employee new salary detail
     */
    @PostMapping("employee/increment")
    public ModelAndView SalaryIncrement(
            @ModelAttribute("salaryTracker") SalaryTracker salaryTracker,
            HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(
                "redirect:" + "/employee/displayEmployee");
        try {
            HttpSession session = request.getSession(false);
            String emailId = request.getParameter("emailId");
            if (employeeService.salaryIncrement(emailId, salaryTracker)) {
                session.setAttribute(EmpConstants.LABEL_MESSAGE,
                        EmpConstants.MSG_UPDATE_SUCCESS);
            } else {
                session.setAttribute(EmpConstants.LABEL_MESSAGE,
                        EmpConstants.MSG_UPDATE_FAIL);
            }
            return modelAndView;
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE,
                    appException.getMessage());
        }
    }
    
    /**
     * Used to add the new project for employee 
     */
    @PostMapping("employee/assignProject")
    public ModelAndView assignProject(HttpServletRequest request,
            ModelMap model) {
        try {
            String emailId = request.getParameter("emailId");
            Integer id = Integer.parseInt(request.getParameter("projectId"));
            Employee employee = employeeService.searchEmployee(emailId);
            Project project = employeeService.getProjectById(id);
            employee.getProjects().add(project);
            employeeService.updateEmployee(employee);
            model.addAttribute(EmpConstants.LABEL_MESSAGE,
                 EmpConstants.MSG_UPDATE_SUCCESS);
            return displayEmployees(model);
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE,
                    appException.getMessage());
        }
    }
    
    /**
     * Used to find the employees by name  
     */
    @PostMapping("employee/searchByName")
    public ModelAndView searchEmployeeByName(HttpServletRequest request) {
        try {
            String name = request.getParameter("name");
            ModelAndView modelAndView = new ModelAndView(ADMINMENU, "command",
                    new TimeSheet());
            modelAndView.addObject("command", new Employee());
            modelAndView.addObject("command", new SalaryTracker());
            modelAndView.addObject("allProjects",
                    employeeService.getAllProjects());
            modelAndView.addObject("allDesignation",
                    employeeService.getDesignations());
            AppLogger.error("arun"+employeeService.searchEmployeeByName(name));
            return modelAndView.addObject(EmpConstants.LABEL_EMPLOYEES,
                    employeeService.searchEmployeeByName(name));
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, EmpConstants.LABEL_MESSAGE,
                    appException.getMessage());
        }
    }
}