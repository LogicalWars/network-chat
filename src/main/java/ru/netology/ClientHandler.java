package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;

public class ClientHandler implements Runnable{
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)){
            String clientString;
            while ((clientString = in.readLine()) != null) {
                System.out.println(LocalDateTime.now() + ": " + clientString);
                Logger.getInstance().log("Error", clientString);
            }

            out.println("Сообщение получено");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
