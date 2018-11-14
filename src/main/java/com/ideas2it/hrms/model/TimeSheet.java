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

import org.hibernate.annotations.SQLDelete;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Project Task model
 * 
 * @author Ganesh Venkat S
 *
 */
@Entity
@Table(name="timesheet")
@SQLDelete(sql="update timesheet set expired_date = current_date() where id=?")
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
