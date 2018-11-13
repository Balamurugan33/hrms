package com.ideas2it.hrms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Used to get the Designation of the employee
 * Not a list of designation, only it's store the single designation detail
 * And used to get the list of employee have a same designation
 * Also get the employee salary and hourly rate based on the designation 
 * it's means the salary and hourly rate varied based on the designation  
 * 
 * @author Balamurugan M
 *
 */
@Entity
@Table(name="designation")
public class Designation {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="name")
    private String name;
    
    @OneToMany(mappedBy="designation", fetch=FetchType.EAGER)
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
}