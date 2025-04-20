package ru.netology.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class FileLoggerTest {

    private static final String TEST_LOG_FILE = "logs.txt";
    private FileLogger fileLogger;

    @BeforeEach
    void setUp() {
        fileLogger = FileLogger.getInstance();
        try {
            Files.deleteIfExists(Paths.get(TEST_LOG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        try {
            Files.deleteIfExists(Paths.get(TEST_LOG_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testLogMessage() throws IOException {
        String level = "INFO";
        String message = "Test message";

        fileLogger.log(level, message);

        File logFile = new File(TEST_LOG_FILE);
        assertTrue(logFile.exists());

        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_LOG_FILE))) {
            String line = reader.readLine();
            assertTrue(line.contains(level));
            assertTrue(line.contains(message));
        }
    }
}