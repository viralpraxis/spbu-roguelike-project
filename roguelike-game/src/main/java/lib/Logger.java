package lib;

class Logger {
    private final static String DEFAULT_LEVEL = "INFO";

    /**
    * This method is used to log message with specified log severity level.
    * @param level Severity level.
    * @param message Message to log.
    */
    public static void log(String level, String message) {
        System.out.println(String.format("[%s] -- %s", level, message));
    }

    /**
    * This method is used to log message with default log severity level.
    * @param message Message to log.
    */
    public static void log(String message) {
        log(DEFAULT_LEVEL, message);
    }
}
