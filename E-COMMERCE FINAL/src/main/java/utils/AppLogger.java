package utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AppLogger {
    private static final String LOG = "app.log";
    public static synchronized void log(String msg) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(LOG, true))) {
            pw.printf("[%s] %s%n", LocalDateTime.now(), msg);
        } catch (Exception ignored) {}
    }
}
