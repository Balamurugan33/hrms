package com.ideas2it.hrms.service.impl;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.hrms.dao.SalaryTrackerDao;
import com.ideas2it.hrms.dao.impl.SalaryTrackerDaoImpl;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.SalaryTracker;
import com.ideas2it.hrms.service.SalaryTrackerService;

public class SalaryTrackerServiceImpl implements SalaryTrackerService{

    private SalaryTrackerDao salaryTrackerDao = new SalaryTrackerDaoImpl();
    
    /** {@inheritDoc}*/
    public Boolean createSalaryTracker(SalaryTracker salaryTracker) 
            throws AppException {
        return salaryTrackerDao.createSalaryTracker(salaryTracker);

    }
    
    /** {@inheritDoc}*/
    public Boolean updateSalaryTracker(SalaryTracker salaryTracker) 
            throws AppException {
        return salaryTrackerDao.updateSalaryTracker(salaryTracker);

    }
    
    /** {@inheritDoc}*/
    public List<SalaryTracker> displaySalaryTrackers() throws AppException {
        return salaryTrackerDao.retrieveSalaryTrackers();
    }
    
    /** {@inheritDoc}*/
    public Boolean deleteSalaryTracker(Integer id) throws AppException {
        return salaryTrackerDao.deleteSalaryTracker(id);
    }
    
    /** {@inheritDoc}*/
    public Boolean isSalaryTrackerExist(String name) throws AppException {
        return (null == salaryTrackerDao.searchSalaryTracker(name));
    }
    
    /** {@inheritDoc}*/
    public SalaryTracker getSalaryTrackerOnDate(LocalDate workedDate, 
        List<SalaryTracker> salaryTrackers) {
        SalaryTracker salaryTracker = null;
        for(SalaryTracker tracker : salaryTrackers) {
            if (workedDate.compareTo(tracker.getUpdateDate()) >= 0) {
                salaryTracker = tracker;
            }
        }
        return salaryTracker;
    }
}