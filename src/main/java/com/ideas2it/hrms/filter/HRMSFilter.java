package com.ideas2it.hrms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2it.hrms.common.FilterConstants;

/**
 * <p>
 * Is used to prevent the unauthorized access in 
 * the admin page and employee page 
 * And also handle the login activities user using session
 * </p>
 *
 * @version 1
 * @author Balamurugan M
 */
public class HRMSFilter implements Filter {
    
    private String HRMSURI = "/hrms/";
    private String USERURI = "/hrms/user/";
    private String LOGIN = "login";
    private String RESOURCES = "/hrms/resources/";
    private String LOGIN_JSP = "/";
    private String ADMIN_MENU_JSP = "/client/revenue";
    private String EMP_VIEW_JSP = "/employee/viewProfile";

    /** Used to initializing the filter */
    public void init(FilterConfig config) throws ServletException {
    
    }
    
    /**
     * Is used to assign the access authentication to user
     * It's means user access to admin page or employee page
     */
    public void doFilter(ServletRequest request, ServletResponse response, 
            FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(Boolean.FALSE);  
        
        httpResponse.setHeader("Cache-Control", "no-cache, no-store");
        httpResponse.setHeader("Pragma","no-cache"); //HTTP 1.0 implementation
        
        Boolean admin = Boolean.FALSE;
        Boolean employee = Boolean.FALSE;
        String role = null;
        
        if (null != session) {
            role = (String)session.getAttribute("role");
            if (null != role) {
                admin = role.equals("Admin");
                employee = role.equals("Employee");
            }
        }
        
        String uri = httpRequest.getRequestURI();
        Boolean employeeUri = uri.endsWith("viewProfile") || 
            uri.endsWith("empProjects") || uri.endsWith("empTimesheet") || 
            uri.endsWith("empAttendance") || uri.endsWith("updateEmployee") 
            || uri.endsWith("updateEntry") || uri.endsWith("empTimesheet") || 
            uri.endsWith("markPresent") || uri.endsWith("markAbsent") || 
            uri.endsWith("createEntry") || uri.endsWith("applyLeave");
        
        if (uri.endsWith(HRMSURI) || uri.startsWith(RESOURCES) || 
                uri.startsWith(USERURI)){
            
            if ((uri.endsWith(HRMSURI) || uri.endsWith(LOGIN)) && 
                    (Boolean.TRUE == admin)) {
                httpRequest.getRequestDispatcher(ADMIN_MENU_JSP).
                    forward(request, response);
            } else if ((uri.endsWith(HRMSURI) || uri.endsWith(LOGIN)) && 
                    (Boolean.TRUE == employee)) {
                httpRequest.getRequestDispatcher(EMP_VIEW_JSP).
                    forward(request, response);
            } else {
                chain.doFilter(request, response);
            }
        
        } else if (Boolean.TRUE == employee && (employeeUri)) {
            chain.doFilter(request, response);
            
        } else if ((Boolean.TRUE == admin)) {
            chain.doFilter(request, response); 
            
            
        } else if (null == session) {
            httpRequest.setAttribute(FilterConstants.LABEL_MESSAGE, 
                    FilterConstants.TIMEOUT_MESSAGE);
            httpRequest.getRequestDispatcher(LOGIN_JSP).
                forward(request, response);
            
        } else {
            session.setAttribute(FilterConstants.LABEL_MESSAGE, 
                    FilterConstants.UNAUTHORITY_MESSAGE);
            httpResponse.sendRedirect(HRMSURI);
        }
    }
    
    public void destroy() {
    
    }
}
