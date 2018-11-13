package com.ideas2it.hrms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.hrms.common.SalaryTrackerConstants;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.logger.AppLogger;
import com.ideas2it.hrms.model.SalaryTracker;
import com.ideas2it.hrms.service.SalaryTrackerService;
import com.ideas2it.hrms.service.impl.SalaryTrackerServiceImpl;

/**
 * Used to perform the different action on salaryTracker 
 * using salaryTracker service class 
 * 
 * @version 1
 * @author Balamurugan M
 */
@Controller
public class SalaryTrackerController {
    
    private String ADMINMENU = "adminHome";
    private String ERROR_PAGE = "error";
    private SalaryTrackerService salaryTrackerService = new SalaryTrackerServiceImpl();
    
    @PostMapping("salaryTracker/createSalaryTracker")
    public ModelAndView createSalaryTracker(@ModelAttribute("salaryTracker") 
            SalaryTracker salaryTracker, ModelMap model) {
        try {
            if(salaryTrackerService.isSalaryTrackerExist(salaryTracker.getName())) {
                if (salaryTrackerService.createSalaryTracker(salaryTracker)) {
                    model.addAttribute(SalaryTrackerConstants.LABEL_MESSAGE,
                        SalaryTrackerConstants.MSG_CREATE_SUCCESS);
                } else {
                    model.addAttribute(SalaryTrackerConstants.LABEL_MESSAGE, 
                        SalaryTrackerConstants.MSG_CREATE_FAIL);
                }
            } else {
                model.addAttribute(SalaryTrackerConstants.LABEL_MESSAGE, 
                    SalaryTrackerConstants.MSG_ALREADY_EXIST);
            }
            return displaySalaryTrackers(model);
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, 
                 SalaryTrackerConstants.LABEL_MESSAGE, appException.getMessage());
        }
    }
    
    @PostMapping("salaryTracker/updateSalaryTracker")
    public ModelAndView updateSalaryTracker(@ModelAttribute("salaryTracker") 
            SalaryTracker salaryTracker, ModelMap model) {
        try {
            if (salaryTrackerService.updateSalaryTracker(salaryTracker)) {
                model.addAttribute(SalaryTrackerConstants.LABEL_MESSAGE,
                    SalaryTrackerConstants.MSG_UPDATE_SUCCESS);
            } else {
                model.addAttribute(SalaryTrackerConstants.LABEL_MESSAGE, 
                    SalaryTrackerConstants.MSG_UPDATE_FAIL);
            }
            return displaySalaryTrackers(model);
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, 
                 SalaryTrackerConstants.LABEL_MESSAGE, appException.getMessage());
        }
    }
    
    @PostMapping("salaryTracker/deleteSalaryTracker")
    public ModelAndView deleteSalaryTracker(HttpServletRequest request, 
            ModelMap model) {
        try {
            if (salaryTrackerService.deleteSalaryTracker(Integer.parseInt(
                    request.getParameter("id")))) {
                model.addAttribute(SalaryTrackerConstants.LABEL_MESSAGE, 
                    SalaryTrackerConstants.MSG_DELETE_SUCCESS);
            } else {
                model.addAttribute(SalaryTrackerConstants.LABEL_MESSAGE,
                    SalaryTrackerConstants.MSG_DELETE_FAIL);
            }
            return displaySalaryTrackers(model);
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, SalaryTrackerConstants.
                 LABEL_MESSAGE,appException.getMessage());
        }
    }
    
    @GetMapping("salaryTracker/displaySalaryTracker")
    public ModelAndView displaySalaryTrackers(ModelMap model) {
        try {
            ModelAndView modelAndView = new ModelAndView(ADMINMENU, 
                "command", new SalaryTracker());
            return modelAndView.addObject(SalaryTrackerConstants.
                LABEL_DESIGNATIONS, salaryTrackerService.displaySalaryTrackers());
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, 
                SalaryTrackerConstants.LABEL_MESSAGE, appException.getMessage());
        }
    }
}