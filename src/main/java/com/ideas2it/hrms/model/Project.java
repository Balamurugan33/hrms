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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

/**
 * Project model
 * 
 * @author Ganesh Venkat S
 *
 */
@Entity
@Table(name="project")
@SQLDelete(sql="update project set expired_date = current_date() where id=?")
public class Project {
    @Id  
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToMany(mappedBy = "project", fetch=FetchType.EAGER)
    private List<TimeSheet> timeSheet = new ArrayList<TimeSheet>();    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name = "project_employee", 
        joinColumns = { @JoinColumn(name = "project_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "employee_id") }
    )
    private List<Employee> employees = new ArrayList<Employee>();
    
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
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public List<TimeSheet> getTimeSheet() {
        return timeSheet;
    }

    public void setTimeSheet(List<TimeSheet> timeSheet) {
        this.timeSheet = timeSheet;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
}
