package com.ideas2it.hrms.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppLogger {
    private static Logger logger = LogManager.getLogger(AppLogger.class.getName());
    
    public void fatal (String logMessage) {
        logger.fatal(logMessage);
    }

    public void error (String logMessage) {
        logger.error(logMessage);
    }

    public void warn (String logMessage) {
        logger.warn(logMessage);
    }

    public void info (String logMessage) {
        logger.info(logMessage);
    }

    public void debug (String logMessage) {
        logger.debug(logMessage);
    }
}
