package com.ideas2it.hrms.controller;

import javax.servlet.http.HttpServletRequest;

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
public class DesignationController {
    
    private String DESIGNATION_VIEW = "designationDisplay";
    private String ADMINMENU = "adminMenu";
    private String ERROR_PAGE = "error";
    private DesignationService designationService = new DesignationServiceImpl();
    
    @PostMapping("/createDesignation")
    public ModelAndView createDesignation(@ModelAttribute("designation") 
            Designation designation) {
        ModelMap model = null;
        try {
            if(!designationService.isDesignationExist(designation.getName())) {
                if (designationService.createDesignation(designation)) {
                    model.addAttribute(DesignationConstants.LABEL_MESSAGE,
                        DesignationConstants.MSG_CREATE_SUCCESS);
                } else {
                    model.addAttribute(DesignationConstants.LABEL_MESSAGE, 
                        DesignationConstants.MSG_CREATE_FAIL);
                }
            }
            model.addAttribute(DesignationConstants.LABEL_MESSAGE, 
                DesignationConstants.MSG_ALREADY_EXIST);
            return displayDesignations(model);
        } catch (AppException appException) {
             return new ModelAndView(ERROR_PAGE, 
                 DesignationConstants.LABEL_MESSAGE, appException.getMessage());
        }
    }
    
    @PostMapping("/updateDesignation")
    public ModelAndView updateDesignation(@ModelAttribute("designation") 
            Designation designation) {
        ModelMap model = null;
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
    
    @PostMapping("/deleteDesignation")
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
    
    @GetMapping("/displayDesignation")
    public ModelAndView displayDesignations(ModelMap model) {
        try {
            return new ModelAndView(DESIGNATION_VIEW, DesignationConstants.
                LABEL_DESIGNATIONS, designationService.displayDesignations());
        } catch (AppException appException) {
            return new ModelAndView(ERROR_PAGE, 
                DesignationConstants.LABEL_MESSAGE, appException.getMessage());
        }
    }
}