package com.ideas2it.hrms.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Attendance model
 * 
 * @author Ganesh Venkat S
 *
 */
@Entity
@Table(name="attendance")
public class Attendance {
    @Id  
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="attend_date")
    private LocalDate attendDate;
    @Column(name="status")
    private boolean status;
    @ManyToMany(mappedBy="attendance", fetch=FetchType.EAGER)
    private List<Employee> employees = new ArrayList<Employee>();
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
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
