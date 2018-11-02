package com.ideas2it.hrms.service.impl;

import java.util.List;

import com.ideas2it.hrms.dao.EmployeeDao;
import com.ideas2it.hrms.dao.impl.EmployeeDaoImpl;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDao employeeDao = new EmployeeDaoImpl();
    
    /** {@inheritDoc}*/
    public Boolean createEmployee(Employee employee) throws AppException {
        return employeeDao.createEmployee(employee);

    }
    
    /** {@inheritDoc}*/
    public Boolean updateEmployee(Employee employee) throws AppException {
        return employeeDao.updateEmployee(employee);

    }
    
    /** {@inheritDoc}*/
    public List<Employee> displayEmployees() throws AppException {
        return employeeDao.retrieveEmployees();
    }
    
    /** {@inheritDoc}*/
    public Boolean deleteEmployee(Integer id) throws AppException {
        return employeeDao.deleteEmployee(id);
    }
    
    /** {@inheritDoc}*/
    public Boolean isEmployeeExist(String emailId) throws AppException {
        return (null == employeeDao.searchEmployee(emailId));
    }
    
    public Employee searchEmployee(String emailId) throws AppException {
        return employeeDao.searchEmployee(emailId);
    }
}