package com.ideas2it.hrms.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.hrms.dao.EmployeeDao;
import com.ideas2it.hrms.dao.impl.EmployeeDaoImpl;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Attendance;
import com.ideas2it.hrms.model.Designation;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.model.Project;
import com.ideas2it.hrms.model.SalaryTracker;
import com.ideas2it.hrms.model.TimeSheet;
import com.ideas2it.hrms.service.AttendanceService;
import com.ideas2it.hrms.service.DesignationService;
import com.ideas2it.hrms.service.EmployeeService;
import com.ideas2it.hrms.service.ProjectService;
import com.ideas2it.hrms.service.TimeSheetService;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    
    /** {@inheritDoc}*/
    public Boolean createEmployee(Employee employee) throws AppException {
        return employeeDao.createEmployee(employee);

    }
    
    public List<Attendance> getAttendanceSheet(Employee employee) throws AppException {
        AttendanceServiceImpl attendService = new AttendanceServiceImpl();
        return attendService.getAttendanceSheet(employee);
    }
    
    public List<Attendance> markPresent(Employee employee) throws AppException {
        AttendanceServiceImpl attendService = new AttendanceServiceImpl();
        return attendService.markPresent(employee);
    }
    
    /** {@inheritDoc}*/
    public List<Attendance> markAbsent(Employee employee) throws AppException {
        AttendanceServiceImpl attendService = new AttendanceServiceImpl();
        return attendService.markAbsent(employee);
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
    public List<Designation> getDesignations() throws AppException {
        DesignationService designationService = new DesignationServiceImpl();
        return designationService.displayDesignations();
    }

    public Integer calculateNetProfit(LocalDate stertDate, LocalDate endDate, 
            Employee employee) {
        Integer billAmount = calculateBillAmount(stertDate, endDate, employee);
        Integer castToCompany 
            = calculateCostToCompany(stertDate, endDate, employee);
        return  billAmount - castToCompany;
    }
    
    /**
     * Calculates the total billable amount, between the two different date
     * from a single employee
     * 
     * @param stertDate
     *        starting working date
     * @param endDate
     *        ending working date
     * @param employee
     *        company employee
     */
    private Integer calculateBillAmount(LocalDate stertDate, LocalDate endDate, 
            Employee employee) {
        Integer billAmount = 0;
        List<TimeSheet> currentTimeSheets = getBetweenTimeSheets(stertDate, 
                endDate, employee.getTimeSheet());
        for (TimeSheet timesheet : currentTimeSheets) {
            
            SalaryTracker currentTracker = getBetweenSalaryTracker(
                timesheet.getEntryDate(), employee.getSalaryTrackers());
            billAmount 
                = timesheet.getWorkedHours() * currentTracker.getHourlyRate();
            
        }
        return billAmount;
    }
    
    /** {@inheritDoc}*/
    public Integer calculateCostToCompany(LocalDate stertDate, 
            LocalDate endDate, Employee employee) {
        Integer costToCompany = 0;
        List<LocalDate> workeddates = calculateBetweenDates(stertDate, endDate);
        for (LocalDate workedDate : workeddates) {
            SalaryTracker currentTracker = getSalaryTracker(workedDate, 
                employee.getSalaryTrackers());
            if (isEmpPresent(workedDate, employee)) {
                Integer dailySalary = currentTracker.getSalary()/30;
                costToCompany = costToCompany + dailySalary;
            }
        }
        return costToCompany; 
    }
    
    /**
     * Used to get the employee time sheet between the two dates 
     * 
     * @param startDate
     *        Starting date of employee time sheet
     * @param endDate
     *        Ending date of employee time sheet
     * @param timesheets
     */
    private List<TimeSheet> getBetweenTimeSheets(LocalDate startDate, 
            LocalDate endDate, List<TimeSheet> timesheets) {
        List<TimeSheet> currentTimeSheets = new ArrayList<TimeSheet>();
        for (TimeSheet timeSheet : timesheets) {
            LocalDate date = timeSheet.getEntryDate();
            
            if ((date.compareTo(startDate) >= 0) 
                    && (date.compareTo(endDate) <= 0)) {
                currentTimeSheets.add(timeSheet);
            }
        }
        return currentTimeSheets;
    }
    
    /**
     * Used to get the salary tracker of particular date
     * 
     * @param workedDate
     *        date to get the employee salary tracker
     * @param salaryTrackers
     */
    private SalaryTracker getSalaryTracker(LocalDate workedDate, 
        List<SalaryTracker> salaryTrackers) {
        SalaryTracker salaryTracker = null;
        for(SalaryTracker tracker : salaryTrackers) {
            if (workedDate.compareTo(tracker.getUpdateDate()) >= 0) {
                salaryTracker = tracker;
            }
        }
        return salaryTracker;
    }
    
    /**
     * Used to get the dates between the two date
     * 
     * @param stertDate
     *        Starting date
     * @param endDate
     *        Ending date 
     */
    private List<LocalDate> calculateBetweenDates(LocalDate stertDate, 
            LocalDate endDate) {
            List<LocalDate> totalDates = new ArrayList<LocalDate>();
            while (!stertDate.isAfter(endDate)) {
                totalDates.add(stertDate);
                stertDate = stertDate.plusDays(1);
            }
            return totalDates;
        }
    
    /**
     * Returns true if employee has present on this day, otherwise false
     * 
     * @param workedDate
     *        date to check the employee is present
     * @param employee
     *        company employee
     */
    private boolean isEmpPresent(LocalDate workedDate, Employee employee) {
        AttendanceService attendanceService = new AttendanceServiceImpl();
        Attendance attendance 
            = attendanceService.getAttendanceStatus(employee, workedDate);
        return attendance.getStatus();
    }
    
    /** {@inheritDoc}*/
    public List<Project> getEmpProjects(List<TimeSheet> tasks) {
        List<Project> projects = new ArrayList<Project>();
        for(TimeSheet task:tasks) {
            if(! projects.contains(task.getProject())) {
                projects.add(task.getProject());
            }
        }
        return projects;
    }
   
    /** {@inheritDoc}*/
    public List<Project> getAllProjects() throws AppException {
        ProjectService projectService = new ProjectServiceImpl();
        return projectService.getAllProjects();
    }

    /** {@inheritDoc}*/
    public boolean createTask(TimeSheet task) throws AppException {
        TimeSheetService sheetService = new TimeSheetServiceImpl();
        return (null != sheetService.createTask(task));
    }

}