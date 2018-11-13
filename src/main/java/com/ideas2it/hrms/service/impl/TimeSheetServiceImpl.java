package com.ideas2it.hrms.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.hrms.dao.TimeSheetDao;
import com.ideas2it.hrms.dao.impl.TimeSheetDaoImpl;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.TimeSheet;
import com.ideas2it.hrms.service.TimeSheetService;

/**
 * <p>
 * Implements TimeSheetService interface
 * </p>
 *
 * @author Ganesh Venkat S
 */
public class TimeSheetServiceImpl implements TimeSheetService {
    
    public TimeSheet createTask(TimeSheet task) throws AppException {
        TimeSheetDao taskDao = new TimeSheetDaoImpl();
        return taskDao.createTask(task);
    }
    
    public TimeSheet getTaskById(Integer id) throws AppException {
        TimeSheetDao taskDao = new TimeSheetDaoImpl();
        return taskDao.getTaskById(id);
    }
    
    public List<TimeSheet> getAllTasks() throws AppException {
        TimeSheetDao taskDao = new TimeSheetDaoImpl();
        return taskDao.getAllTasks();
    }
    
    public TimeSheet updateTask(TimeSheet task) throws AppException {
        TimeSheetDao taskDao = new TimeSheetDaoImpl();
        return taskDao.updateTask(task);
    }

    public TimeSheet removeTask(TimeSheet task) throws AppException {
        TimeSheetDao taskDao = new TimeSheetDaoImpl();
        return taskDao.removeTask(task);
    }

    public List<TimeSheet> getTimeSheetEntries(List<TimeSheet> timeSheet, LocalDate startDate, LocalDate endDate) {
        List<TimeSheet> timeSheetEntries = new ArrayList<TimeSheet>();

        for (TimeSheet entry : timeSheet) {
            if (isEntryBetweenPeriod(entry, startDate, endDate)) {
                timeSheetEntries.add(entry);
            }
        }

        return timeSheetEntries;  
    }

    public boolean isEntryBetweenPeriod(TimeSheet entry, LocalDate startDate, LocalDate endDate) {
        LocalDate entryDate = entry.getEntryDate();
        boolean isValidEntry = false;

        if (entryDate.isAfter(startDate) && entryDate.isBefore(endDate)) {
            isValidEntry = true;
        }
        
        return isValidEntry;
    }
}
