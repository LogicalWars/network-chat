package ru.netology;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private static Logger INSTANCE = null;
    private final String PATH_TO_FILE = "logs.txt";

    private Logger() {
    }

    public static Logger getInstance() {
        if (INSTANCE == null) {
            synchronized (Logger.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Logger();
                }
            }
        }
        return INSTANCE;
    }

    public void log(String level, String message) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(PATH_TO_FILE, true))) {
            out.write(LocalDateTime.now() + ": " + message + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
