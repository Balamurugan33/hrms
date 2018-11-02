package com.ideas2it.hrms.controller;

import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import com.ideas2it.hrms.common.UserConstants;
import com.ideas2it.hrms.exception.AppException;
import com.ideas2it.hrms.model.Employee;
import com.ideas2it.hrms.model.User;
import com.ideas2it.hrms.service.UserService;
import com.ideas2it.hrms.service.impl.UserServiceImpl;
import com.ideas2it.hrms.logger.AppLogger;
/**
 * <p>
 * The {@code UserController} is used to perform the deferent action 
 * on user details by using the UserServiceImpl class
 * </p>
 *
 * @version 1
 * @author Balamurugan
 */
@Controller
public class UserController {
    
    private String LOGIN_JSP = "login";
    private String ADMIN_JSP = "adminMenu";
    private String EMPLOYEE_VIEW = "employeeMenu";
    private String CUSTOMERCREATE_JSP = "employeeCreate";
    private String ERROR_JSP = "error";
    private String LABEL_ADMIN = "admin";
    
    private UserService userService = new UserServiceImpl();
    
    /** Used to open the login page */
    @GetMapping("/")
    public ModelAndView viewLogin() {
        return new ModelAndView(LOGIN_JSP, "command", new User());
    }
      
    /** Is used to create the user in hrms*/
    @PostMapping("user/register")
    public ModelAndView createUser(@ModelAttribute("user")User user, 
            HttpServletRequest request) {     
        try {
            ModelAndView modelAndView = new ModelAndView(LOGIN_JSP, "command",
                new User());
            if (null != userService.checkEmployeeDetail(user.getUserName())) {
                user.setRole(request.getParameter(UserConstants.LABEL_ROLE));
                if (userService.createUser(user)) {
                    return modelAndView.addObject(UserConstants.LABEL_MESSAGE, 
                        UserConstants.MSG_CREATE_SUCCESS);
                } else {
                    return modelAndView.addObject(UserConstants.LABEL_MESSAGE, 
                        UserConstants.MSG_CREATE_FAIL);
                }
            } else {
                return modelAndView.addObject(UserConstants.LABEL_MESSAGE, 
                    UserConstants.MSG_USER_NAME_NOTEXIST);
            }
        } catch (AppException dvdException) {
            return new ModelAndView(ERROR_JSP, UserConstants.LABEL_MESSAGE, 
                dvdException.getMessage());
        }  
    } 
    
    /**
     * Used to find the user in the hrms
     */
    @PostMapping("user/login")
    public ModelAndView login(@ModelAttribute("user")User user, 
            HttpServletRequest request) {
        try {
            ModelAndView modelAndView = new ModelAndView(LOGIN_JSP, "command",
                new User());
            User oldUser = userService.searchUser(user.getUserName(), 
                user.getPassword(), user.getRole());
            if (null != oldUser) {
                    return setUserRole(oldUser, request);
            } else {
                return modelAndView.addObject(UserConstants.LABEL_MESSAGE, 
                    UserConstants.MSG_INVALID_LOGIN);
            }
        } catch (AppException dvdException) {
            return new ModelAndView(ERROR_JSP, UserConstants.LABEL_MESSAGE, 
                dvdException.getMessage());
        }  
    }
    
    /**
     * Used to decide the user is admin or employee
     * 
     * @param user
     *       Used to get the user class reference
     */
    private ModelAndView setUserRole(User user, HttpServletRequest request) {
        try {
            String role = request.getParameter(UserConstants.LABEL_ROLE);
            HttpSession session = request.getSession(Boolean.TRUE);
            session.setMaxInactiveInterval(60); 
            session.setAttribute(UserConstants.LABEL_ROLE, role);
            if (role.equals(LABEL_ADMIN)) {
                session.setAttribute(UserConstants.LABEL_ID, user.getId());
                return new ModelAndView(ADMIN_JSP);
            } else { 
                Employee employee 
                    = userService.checkEmployeeDetail(user.getUserName());
                if (null != employee) {
                    session.setAttribute(UserConstants.LABEL_EMPLOYEE, employee);
                    return new ModelAndView(EMPLOYEE_VIEW);
                } else {
                    ModelAndView modelAndView = new ModelAndView(
                        CUSTOMERCREATE_JSP, "command", new Employee());
                    return modelAndView.addObject(UserConstants.LABEL_ID, 
                        user.getId());
                }
            }
        } catch (AppException dvdException) {
            return new ModelAndView(ERROR_JSP, UserConstants.LABEL_MESSAGE, 
                dvdException.getMessage());
        }
    }
    
    // Used to invalidate the session
    @GetMapping("user/logout")
    public ModelAndView logOut(HttpServletRequest request) {
        request.getSession(Boolean.FALSE).invalidate();
        return new ModelAndView(LOGIN_JSP, "command", new User());
    }
}