package ru.netology;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static final int MAX_THREAD = 1000;

    public static void main(String[] args) {
        new ServerProperties();
        try (ServerSocket serverSocket = new ServerSocket(ServerProperties.getServerPort())) {
            try (ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD)){
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    executorService.submit(new ClientHandler(clientSocket));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}