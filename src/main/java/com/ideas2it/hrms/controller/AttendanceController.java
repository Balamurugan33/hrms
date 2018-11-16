package com.ideas2it.hrms.controller;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
            attendance = attendanceService.createAttendance(attendance);   
            modelAndView.addObject("Success", MSG_CREATED);
            modelAndView.setViewName("attendance");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
       return modelAndView;
    }
    
    @PostMapping("leave")
    public ModelAndView Leave(@ModelAttribute("attendance") Attendance attendance, 
        HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Integer empId = 0;
        String message = null;
        
        empId = Integer.parseInt(request.getParameter("leaveEmpId"));
        message = request.getParameter("reason");
        
    try {
        Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    Session session = Session.getDefaultInstance(props, null);
    session.setDebug(true);
    Message msg = new MimeMessage(session);
        msg.setText(message);
        msg.setFrom(new InternetAddress("ganeshvenkat@ideas2it.com"));
        msg.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("ganeshvenkat@ideas2it.com")});
        msg.setSubject("Subject Line");
        Transport.send(msg);
    }  catch (MessagingException e) {
        e.printStackTrace();
    }
    modelAndView.setViewName("employeeView");
        return modelAndView;
    }
    
    @PostMapping("update")
    public ModelAndView updateAttendance(
        @ModelAttribute("attendance") Attendance attendance, 
        HttpServletRequest request) {
        AttendanceService attendanceService = new AttendanceServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            attendance = attendanceService.updateAttendance(attendance);
            modelAndView.addObject("Success", MSG_UPDATED);
            modelAndView.setViewName("attendance");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
        return modelAndView;
    }
         
    @GetMapping("displayAll")
    public ModelAndView displayAllAttendances(HttpServletRequest request) {
        AttendanceService attendanceService = new AttendanceServiceImpl();
        ModelAndView modelAndView = new ModelAndView(); 

        try {
            List<Attendance> allAttendances = attendanceService.getAllAttendances();
            modelAndView.addObject("allAttendances", allAttendances);
            modelAndView.setViewName("attendance");
        } catch (AppException appException) {
            modelAndView.addObject("Error", appException.getMessage());
        }
        return modelAndView;
    }
}