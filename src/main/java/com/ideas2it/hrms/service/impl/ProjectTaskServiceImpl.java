package com.ideas2it.hrms.service.impl;

import java.util.List;

import com.ideas2it.hrms.dao.ProjectTaskDao;
import com.ideas2it.hrms.dao.impl.ProjectTaskDaoImpl;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.ProjectTask;

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
}
