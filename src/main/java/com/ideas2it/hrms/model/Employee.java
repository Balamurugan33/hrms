package com.ideas2it.hrms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Employee {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="name=name")
    private String name;
    
    @Column(name="name=email_id")
    private String emailId;
    
    @Column(name="name=mobile_number")
    private String mobileNo;
    
    @OneToMany(mappedBy="Employee", fetch=FetchType.EAGER)
    private List<ProjectTask> projectTask = new ArrayList<ProjectTask>();
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="emp_attendance",
        joinColumns = { @JoinColumn(name = "emp_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "attendance_id") }
    )
    private List<Attendance> attendance = ArrayList<Attendance>();
    
    @ManyToOne
    @JoinColumn(name="designation_id")
    private Designation designation;

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
    
}