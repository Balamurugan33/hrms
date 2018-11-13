package com.ideas2it.hrms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Used to get the employee details it's means single employee details
 * And also used to get the designation, 
 * from designation to get the employee salary and role 
 * Used to get the List of attendance it's single employee present and absent dates
 * 
 * @author Balamurugan M
 *
 */
@Entity
@Table(name="employee")
public class Employee {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="email_id")
    private String emailId;
    
    @Column(name="mobile_number")
    private String mobileNo;
    
    @Column(name="hourly_rate")
    private Integer hourlyRate;
    
    @OneToMany(mappedBy="employee")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<TimeSheet> timeSheet = new ArrayList<TimeSheet>();
    
    @OneToMany(mappedBy="employee", fetch=FetchType.EAGER)
    private List<Attendance> attendance = new ArrayList<Attendance>();
    
    @ManyToOne
    @JoinColumn(name="designation_id")
    private Designation designation;
    
    @Column(name="salary")
    private Integer salary;
    
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public List<TimeSheet> getTimeSheet() {
        return timeSheet;
    }

    public void setTimeSheet(List<TimeSheet> timeSheet) {
        this.timeSheet = timeSheet;
    }

    public List<Attendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(List<Attendance> attendance) {
        this.attendance = attendance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }
    
    public Integer getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Integer hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    
}