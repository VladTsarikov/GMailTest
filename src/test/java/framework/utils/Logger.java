package framework.utils;

import framework.enums.LogType;

public class Logger {

    private static final org.apache.log4j.Logger LOG4J = org.apache.log4j.Logger.getLogger(Logger.class);

    public static void logStep(int step, Object msg) {
        info(String.format("--------==[ %1$s ]==--------", step));
        info(msg);
    }

    private static void info(Object message) {
        LOG4J.info(message);
    }

    public static void log(LogType logType, Object message){
        switch (logType) {
            case DEBUG:
                LOG4J.debug(message);
                break;
            case ERROR:
                LOG4J.error(message);
                break;
            case FATAL:
                LOG4J.fatal(message);
                break;
            case WARN:
                LOG4J.warn(message);
                break;
            case INFO:
                LOG4J.info(message);
                break;
        }
    }

    public static void log(Object message){
        info(message);
    }
}