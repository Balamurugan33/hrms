package com.ideas2it.hrms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.hrms.common.DesignationConstants;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Designation;
import com.ideas2it.hrms.service.DesignationService;
import com.ideas2it.hrms.service.impl.DesignationServiceImpl;

/**
 * Used to perform the different action on designation 
 * using designation service class 
 * 
 * @version 1
 * @author Balamurugan M
 */
@Controller
public class DesignationController {
    
    private String ADMINMENU = "adminHome";
    private String ERROR_PAGE = "error";
    private DesignationService designationService = new DesignationServiceImpl();
    
    /**
     * Used to create the new designation of employee
     * 
     * @param designation
     *        Get the designation information
     */
    @PostMapping("designation/createDesignation")
    public ModelAndView createDesignation(@ModelAttribute("designation") 
            Designation designation, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(
                "redirect:" + "/designation/displayDesignation");
        try {
            HttpSession session = request.getSession(false);
            if(designationService.isDesignationExist(designation.getName())) {
                if (designationService.createDesignation(designation)) {
                    session.setAttribute(DesignationConstants.LABEL_MESSAGE,
                        DesignationConstants.MSG_CREATE_SUCCESS);
                } else {
                    session.setAttribute(DesignationConstants.LABEL_MESSAGE, 
                        DesignationConstants.MSG_CREATE_FAIL);
                }
            } else {
                session.setAttribute(DesignationConstants.LABEL_MESSAGE, 
                    DesignationConstants.MSG_ALREADY_EXIST);
            }
            return modelAndView;
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, 
                 DesignationConstants.LABEL_MESSAGE, appException.getMessage());
        }
    }
    
    /**
     * Used to update the designation
     * 
     * @param designation
     *        Get the designation information
     */
    @PostMapping("designation/updateDesignation")
    public ModelAndView updateDesignation(@ModelAttribute("designation") 
            Designation designation, ModelMap model) {
        try {
            if (designationService.updateDesignation(designation)) {
                model.addAttribute(DesignationConstants.LABEL_MESSAGE,
                    DesignationConstants.MSG_UPDATE_SUCCESS);
            } else {
                model.addAttribute(DesignationConstants.LABEL_MESSAGE, 
                    DesignationConstants.MSG_UPDATE_FAIL);
            }
            return displayDesignations(model);
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, 
                 DesignationConstants.LABEL_MESSAGE, appException.getMessage());
        }
    }
    
    /**
     * Used to delete the designation 
     */
    @PostMapping("designation/deleteDesignation")
    public ModelAndView deleteDesignation(HttpServletRequest request, 
            ModelMap model) {
        try {
            if (designationService.deleteDesignation(Integer.parseInt(
                    request.getParameter("id")))) {
                model.addAttribute(DesignationConstants.LABEL_MESSAGE, 
                    DesignationConstants.MSG_DELETE_SUCCESS);
            } else {
                model.addAttribute(DesignationConstants.LABEL_MESSAGE,
                    DesignationConstants.MSG_DELETE_FAIL);
            }
            return displayDesignations(model);
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, DesignationConstants.
                 LABEL_MESSAGE,appException.getMessage());
        }
    }
    
    /**
     * Used to display the all designations
     */
    @GetMapping("designation/displayDesignation")
    public ModelAndView displayDesignations(ModelMap model) {
        try {
            ModelAndView modelAndView = new ModelAndView(ADMINMENU, 
                "command", new Designation());
            return modelAndView.addObject(DesignationConstants.
                LABEL_DESIGNATIONS, designationService.displayDesignations());
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, 
                DesignationConstants.LABEL_MESSAGE, appException.getMessage());
        }
    }
    
    /**
     * Used to display the employees have a same designation 
     */
    @GetMapping("designation/getEmployees")
    public ModelAndView displayEmployees(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView(); 
        Designation designation = new Designation();
        designation.setId(Integer.parseInt(request.getParameter("designationId")));
        designation.getEmployees();
        modelAndView.addObject("timeSheets", designation.getEmployees());
        return modelAndView;
    }
}