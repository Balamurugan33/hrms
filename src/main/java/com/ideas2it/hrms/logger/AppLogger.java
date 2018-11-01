package com.ideas2it.hrms.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>
 * Application level logger
 * </p>
 * 
 * @author Ganesh Venkat S
 */

public class AppLogger {
    private static Logger logger = 
        LogManager.getLogger(AppLogger.class.getName());
    
    public static void fatal (String logMessage) {
        logger.fatal(logMessage);
    }

    public static void error (String logMessage) {
        logger.error(logMessage);
    }

    public static void warn (String logMessage) {
        logger.warn(logMessage);
    }

    public static void info (String logMessage) {
        logger.info(logMessage);
    }

    public static void debug (String logMessage) {
        logger.debug(logMessage);
    }

    public static void error(String message, Exception exception) {
        logger.error(message, exception);
    }
        
    public static void fatal(String message, Exception exception) {
        logger.fatal(message, exception);
    }
}
