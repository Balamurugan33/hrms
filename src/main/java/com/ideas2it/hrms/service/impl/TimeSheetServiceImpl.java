package com.ideas2it.hrms.service.impl;

import java.time.Duration;
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
    
    public List<TimeSheet> getCurrentMonthTasks(List<TimeSheet> tasks) {
        List<TimeSheet> curMonthTasks = new ArrayList<TimeSheet>();

        for (TimeSheet task : tasks) {
            if (isCurrentMonthTask(task)) {
                curMonthTasks.add(task);
            }
        }

        return curMonthTasks;  
    }

    public boolean isCurrentMonthTask(TimeSheet task) {
        LocalDate today = LocalDate.now();        
        LocalDate taskDate = task.getEntryDate();
        boolean isCurMonthTask = false;

        if (taskDate.getMonth() == today.getMonth() && taskDate.getYear() == today.getYear()) {
           isCurMonthTask = true;
        } 

        return isCurMonthTask;
    }       
    
    public Integer calculateTaskDuration(TimeSheet task) {
        return null;
    }
}
