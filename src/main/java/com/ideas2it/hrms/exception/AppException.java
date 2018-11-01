package com.ideas2it.hrms.exception;

public class AppException extends Exception {
    public AppException(String customMessage) {
        // Call the super class constructor
        super(customMessage);
    }

    public AppException(String customMessage, Throwable cause) {
        // Call the super class constructor
        super(customMessage, cause);
    }
}
