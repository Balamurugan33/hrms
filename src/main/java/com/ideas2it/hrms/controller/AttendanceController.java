package com.ideas2it.hrms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Attendance;
import com.ideas2it.hrms.service.AttendanceService;
import com.ideas2it.hrms.service.impl.AttendanceServiceImpl;

import static com.ideas2it.hrms.common.AttendanceConstants.MSG_CREATED;
import static com.ideas2it.hrms.common.AttendanceConstants.MSG_DELETED;
import static com.ideas2it.hrms.common.AttendanceConstants.MSG_UPDATED;


/**
 * Provides functionality to manage an employee's attendance
 * 
 * @author Ganesh Venkat S
 */
@Controller 
@RequestMapping("attendance") 
public class AttendanceController {
            
    @PostMapping("create")
    public ModelAndView createAttendance(
            @ModelAttribute("attendance") Attendance attendance, 
            HttpServletRequest request) {
        AttendanceService attendanceService = new AttendanceServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            // Check if duplicate attendance exists including deleted attendances
            attendance = attendanceService.createAttendance(attendance);   
            modelAndView.addObject("Success", MSG_CREATED);
            // redirect him to the same page; the attendances must also be sent
            // alert box is optional for now
            modelAndView.setViewName("attendance");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
       return modelAndView;
    }
    
    @PostMapping("update")
    public ModelAndView updateAttendance(
        @ModelAttribute("attendance") Attendance attendance, 
        HttpServletRequest request) {
        AttendanceService attendanceService = new AttendanceServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            // Check if the attendance to be updated exists 
            attendance = attendanceService.updateAttendance(attendance);
            modelAndView.addObject("Success", MSG_UPDATED);
            // redirect him to the same page; the attendances must also be sent
            // alert box is optional for now
            modelAndView.setViewName("attendance");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
        return modelAndView;
    }
        
    @PostMapping("delete")
    public ModelAndView deleteAttendance(HttpServletRequest request) {
        AttendanceService attendanceService = new AttendanceServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            // Check if the attendance to be deleted exists 
            Integer id = Integer.parseInt(request.getParameter("id"));
            Attendance attendance = attendanceService.getAttendanceById(id);
            if (null != attendance) {
                attendance = attendanceService.removeAttendance(attendance);    
            }
            modelAndView.addObject("Success", MSG_DELETED);
            // redirect him to the same page; the attendances must also be sent
            // alert box is optional for now
            modelAndView.setViewName("attendance");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());  
        }
        return modelAndView;
    }
    
    // Check if this method is redundant
    @GetMapping("displayAll")
    public ModelAndView displayAllAttendances(HttpServletRequest request) {
        AttendanceService attendanceService = new AttendanceServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            List<Attendance> allAttendances = attendanceService.getAllAttendances();
            modelAndView.addObject("allAttendances", allAttendances);
            // redirect him to the same page; the attendances must also be sent
            // alert box is optional for now
            modelAndView.setViewName("attendance");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
        return modelAndView;
    }
}