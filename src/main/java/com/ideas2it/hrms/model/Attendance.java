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
import org.hibernate.annotations.Where;

/**
 * Used to track the daily attendance of an employee.
 * An employee has many daily attendance entries, which together form the employee's attendance history.
 * The status field represents whether the employee was: Present or Absent(on leave) on that date.
 * 
 * @author Ganesh Venkat S
 *
 */
@Entity
@Table(name="attendance")
@SQLDelete(sql="update attendance set expired_date = current_date() where id=?")
@Where(clause = "expired_date is null")
public class Attendance {
    
    @Id  
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="attend_date")
    private LocalDate attendDate;
    
    @Column(name="status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getAttendDate() {
        return attendDate;
    }

    public void setAttendDate(LocalDate attendDate) {
        this.attendDate = attendDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
