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
 * Used to get the single employee salary details
 * When the salary of employee is changed the new entry is created
 * And also it's store the salary changing date for future tracking
 *   
 * @author Balamurugan M
 *
 */
@Entity
@Table(name="salary_tracker")
@SQLDelete(sql="update salary_tracker set expired_date = current_date() where id=?")
public class SalaryTracker {
    
    @Id  
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="salary")
    private Integer salary;
    
    @Column(name="hourly_rate")
    private Integer hourlyRate;
    
    @Column(name="update_date")
    private LocalDate updateDate;
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Integer hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getUpdateDate() {
        return updateDate;
    }
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}