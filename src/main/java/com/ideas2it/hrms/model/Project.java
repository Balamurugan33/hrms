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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Project other = (Project) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
