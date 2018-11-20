package com.ideas2it.hrms.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.SQLDelete;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Represents an employee's timesheet entry.
 * Used to track the number of hours worked and the projects worked on by an employee in a single day.
 * An employee has many daily timesheet entries, which together form the employee's timesheet history.
 * It contains: all projects he's worked on and number of hours worked on each project, 
 * for each entry (working day)
 * 
 * @author Ganesh Venkat S
 */
@Entity
@Table(name="timesheet")
@SQLDelete(sql="update timesheet set expired_date = current_date() where id=?")
@FilterDef(name = "timesheetFilter", defaultCondition=" expired_date is null")
@Filter(name = "timesheetFilter")   
public class TimeSheet {
    
    @Id  
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="entry_date")
    private LocalDate entryDate;
    
    @Column(name="worked_hours")
    private Integer workedHours;
    
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getEntryDate() {
        return entryDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getWorkedHours() {
        return workedHours;
    }
    
    public void setWorkedHours(Integer workedHours) {
        this.workedHours = workedHours;
    }
    
    public Project getProject() {
        return project;
    }
    
    public void setProject(Project project) {
        this.project = project;
    }
    
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }    
}
