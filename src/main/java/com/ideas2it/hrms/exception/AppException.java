package com.ideas2it.hrms.exception;

/**
 * <p>
 * Application level exception handler
 * Appends wrapped custom exceptions with relevant message and forwards it to
 * the parent class: Exception 
 * </p>
 * 
 * @author Ganesh Venkat S
 */

public class AppException extends Exception {
    public AppException(String customMessage) {
        super(customMessage);
    }

    public AppException(String customMessage, Throwable cause) {
        super(customMessage, cause);
    }
}
