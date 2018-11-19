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
    
    public TimeSheet createEntry(TimeSheet entry) throws AppException {
        TimeSheetDao entryDao = new TimeSheetDaoImpl();
        return entryDao.createEntry(entry);
    }
    
    public TimeSheet getEntryById(Integer id) throws AppException {
        TimeSheetDao entryDao = new TimeSheetDaoImpl();
        return entryDao.getEntryById(id);
    }
    
    public List<TimeSheet> getAllEntries() throws AppException {
        TimeSheetDao entryDao = new TimeSheetDaoImpl();
        return entryDao.getAllEntries();
    }
    
    public TimeSheet updateEntry(TimeSheet entry) throws AppException {
        TimeSheetDao entryDao = new TimeSheetDaoImpl();
        return entryDao.updateEntry(entry);
    }

    public TimeSheet removeEntry(TimeSheet entry) throws AppException {
        TimeSheetDao entryDao = new TimeSheetDaoImpl();
        return entryDao.removeEntry(entry);
    }

    public List<TimeSheet> getEntriesInInterval(List<TimeSheet> timeSheet, LocalDate startDate, LocalDate endDate) {
        List<TimeSheet> timeSheetEntries = new ArrayList<TimeSheet>();

        for (TimeSheet entry : timeSheet) {
            if (isEntryInInterval(entry, startDate, endDate)) {
                timeSheetEntries.add(entry);
            }
        }
        return timeSheetEntries;  
    }

    public boolean isEntryInInterval(TimeSheet entry, LocalDate startDate, LocalDate endDate) {
        LocalDate entryDate = entry.getEntryDate();
        boolean isValidEntry = false;

        if (entryDate.isAfter(startDate) && entryDate.isBefore(endDate) 
                || entryDate.equals(startDate) || entryDate.equals(endDate)) {
            isValidEntry = true;
        }        
        return isValidEntry;
    }
}
