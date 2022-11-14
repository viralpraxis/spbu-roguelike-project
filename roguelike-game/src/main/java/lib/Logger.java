package lib;

class Logger {
    private final static String DEFAULT_LEVEL = "INFO";

    public static void log(String level, String message) {
        System.out.println(String.format("[%s] -- %s", level, message));
    }
    public static void log(String message) {
        log(DEFAULT_LEVEL, message);
    }
}
