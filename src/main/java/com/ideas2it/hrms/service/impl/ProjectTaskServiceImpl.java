package com.ideas2it.hrms.service.impl;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.hrms.dao.ProjectTaskDao;
import com.ideas2it.hrms.dao.impl.ProjectTaskDaoImpl;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.ProjectTask;
import com.ideas2it.hrms.service.ProjectTaskService;

/**
 * <p>
 * Implements ProjectTaskService interface
 * </p>
 *
 * @author Ganesh Venkat S
 */
public class ProjectTaskServiceImpl implements ProjectTaskService {
    
    public ProjectTask createTask(ProjectTask task) throws AppException {
        ProjectTaskDao taskDao = new ProjectTaskDaoImpl();
        return taskDao.createTask(task);
    }
    
    public ProjectTask getTaskById(Integer id) throws AppException {
        ProjectTaskDao taskDao = new ProjectTaskDaoImpl();
        return taskDao.getTaskById(id);
    }
    
    public List<ProjectTask> getAllTasks() throws AppException {
        ProjectTaskDao taskDao = new ProjectTaskDaoImpl();
        return taskDao.getAllTasks();
    }
    
    public ProjectTask updateTask(ProjectTask task) throws AppException {
        ProjectTaskDao taskDao = new ProjectTaskDaoImpl();
        return taskDao.updateTask(task);
    }

    public ProjectTask removeTask(ProjectTask task) throws AppException {
        ProjectTaskDao taskDao = new ProjectTaskDaoImpl();
        return taskDao.removeTask(task);
    }
    
    public List<ProjectTask> getCurrentMonthTasks(List<ProjectTask> tasks) {
        List<ProjectTask> curMonthTasks = new ArrayList<ProjectTask>();

        for (ProjectTask task : tasks) {
            if (isCurrentMonthTask(task)) {
                curMonthTasks.add(task);
            }
        }

        return curMonthTasks;  
    }

    public boolean isCurrentMonthTask(ProjectTask task) {
        LocalDate today = LocalDate.now();        
        LocalDate taskDate = task.getTaskDate();
        boolean isCurMonthTask = false;

        if (taskDate.getMonth() == today.getMonth() && taskDate.getYear() == today.getYear()) {
           isCurMonthTask = true;
        } 

        return isCurMonthTask;
    }       
    
    public Integer calculateTaskDuration(ProjectTask task) {
        Duration duration
            = Duration.between(task.getEndTime(), task.getStartTime());

        return (int) duration.toHours();
    }
}
