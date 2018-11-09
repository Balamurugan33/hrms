package com.ideas2it.hrms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * It's contains the details of the user in the HRMS 
 * And it's not store the personal details of user
 * It's only store the login details of the user like user name, password
 * </p>
 *
 * @version 1
 * @author Balamurugan M
 */
@Entity
@Table(name="user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer Id;
    
    @Column(name="username")
    private String userName;
    
    @Column(name="password")
    private String password;
    
    @Column(name="role")
    private String role;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}    