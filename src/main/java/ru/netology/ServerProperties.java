package ru.netology;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ServerProperties {
    private static final String PATH_TO_SETTINGS = "src/main/resources/settings.txt";
    private static Integer serverPort;

    public ServerProperties() {
        try (BufferedReader in = new BufferedReader(new FileReader(PATH_TO_SETTINGS))) {
            serverPort = Integer.valueOf(in.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer getServerPort() {
        return serverPort;
    }
}
