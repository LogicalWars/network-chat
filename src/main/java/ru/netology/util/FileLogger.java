package ru.netology.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileLogger implements Logger {
    private static FileLogger INSTANCE = null;
    private final String PATH_TO_FILE = "logs.txt";

    private FileLogger() {
    }

    public static FileLogger getInstance() {
        if (INSTANCE == null) {
            synchronized (FileLogger.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FileLogger();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void log(String level, String message) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(PATH_TO_FILE, true))) {
            out.write("[%s][%s]: %s\n".formatted(LocalDateTime.now(), level, message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
