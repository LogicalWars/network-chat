package ru.netology;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.netology.entity.Message;
import ru.netology.util.FileLogger;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final ChatServer server;
    protected PrintWriter out;
    ObjectMapper mapper = new ObjectMapper();
    BufferedReader in;

    public ClientHandler(Socket socket, ChatServer server) {
        this.clientSocket = socket;
        this.server = server;
        server.addClient(this);
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Выносим создание
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            String jsonInput;
            while ((jsonInput = in.readLine()) != null) {
                Message message = mapper.readValue(jsonInput, Message.class);
                System.out.printf("%s %s: %s%n", message.getDateTime(), message.getName(), message.getMessage());
                FileLogger.getInstance().log("INFO", message);
                server.broadcastMessage(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            out.close();
            server.removeClient(this);
        }
    }

    public void sendMessage(Message message) {
        try {
            String json = mapper.writeValueAsString(message);
            out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
