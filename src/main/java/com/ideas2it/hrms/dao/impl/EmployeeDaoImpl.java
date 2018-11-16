package com.ideas2it.hrms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.hrms.common.EmpConstants;
import com.ideas2it.hrms.dao.EmployeeDao;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.session.HibernateSession;

public class EmployeeDaoImpl implements EmployeeDao {
    
    private Session session = null;
    private Transaction transaction = null;
    
    /** {@inheritDoc}*/
    public Boolean createEmployee(Employee employee) throws AppException {
        try {
            session = HibernateSession.getSession();
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(EmpConstants.ERROR_CREATE_EMPLOYEE+employee.
                getName(), e);
            throw new AppException(EmpConstants.ERROR_CREATE_EMPLOYEE+
                employee.getName());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean updateEmployee(Employee employee) throws AppException {
        try {
            session = HibernateSession.getSession();
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(EmpConstants.ERROR_UPDATE_EMPLOYEE+
                employee.getName(), e);
            throw new AppException(EmpConstants.ERROR_UPDATE_EMPLOYEE+
                employee.getName());
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public List<Employee> retrieveEmployees() throws AppException {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            session = HibernateSession.getSession();
            employees = session.createQuery("FROM Employee").list();
            return employees;
        } catch (HibernateException e) {
            AppLogger.error(EmpConstants.ERROR_RETRIEVE_EMPLOYEE, e);
            throw new AppException(EmpConstants.ERROR_RETRIEVE_EMPLOYEE);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc}*/
    public Boolean deleteEmployee(Integer id) throws AppException {
        try {
            session = HibernateSession.getSession();
            transaction = session.beginTransaction();
            Employee employee = (Employee)session.get(Employee.class, id);
            session.delete(employee);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            AppLogger.error(EmpConstants.ERROR_DELETE_EMPLOYEE+id, e);
            throw new AppException(EmpConstants.ERROR_DELETE_EMPLOYEE+id);
        } finally {
            session.close(); 
        }
    }
    
    /** {@inheritDoc} */
    public Employee searchEmployee(String email) throws AppException {
        try {
            session = HibernateSession.getSession();
            String query = "FROM Employee WHERE emailId = :email";
            return (Employee)session.createQuery(query).
                setParameter("email", email ).uniqueResult();
        } catch (HibernateException e) {
            AppLogger.error(EmpConstants.ERROR_RETRIEVE_EMPLOYEE, e);
            throw new AppException(EmpConstants.ERROR_RETRIEVE_EMPLOYEE);
        } finally {
            session.close(); 
        }
    }
}