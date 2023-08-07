package com.model.cbehavioral.responsibility;

import com.model.cbehavioral.responsibility.service.AbstractLogger;
import com.model.cbehavioral.responsibility.service.impl.ConsoleLogger;
import com.model.cbehavioral.responsibility.service.impl.ErrorLogger;
import com.model.cbehavioral.responsibility.service.impl.FileLogger;

/**
 * @author 厚积薄发
 * @create 2023-05-19-16:47
 */
public class ChainPatternDemo {

    private static AbstractLogger getChainOfLoggers(){

        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();

        loggerChain.logMessage(AbstractLogger.INFO, "This is an information.");

        loggerChain.logMessage(AbstractLogger.DEBUG,
                "This is a debug level information.");

        loggerChain.logMessage(AbstractLogger.ERROR,
                "This is an error information.");
    }
}