package com.ideas2it.hrms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Designation {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="base_pay")
    private Integer basePay;
    
    @Column(name="salary")
    private Integer salary;
    
    @OneToMany(mappedBy="Designation", fetch=FetchType.EAGER)
    private List<Employee> employees = new ArrayList<Employee>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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

    public Integer getBasePay() {
        return basePay;
    }

    public void setBasePay(Integer basePay) {
        this.basePay = basePay;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}