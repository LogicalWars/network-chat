package ru.netology;

import ru.netology.entity.Message;

import java.util.concurrent.CopyOnWriteArrayList;

public class ChatServer {
    private final CopyOnWriteArrayList<Message> messageHistory = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public void addClient(ClientHandler client) {
        clients.add(client);
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public void broadcastMessage(Message message) {
        messageHistory.add(message);  // Сохраняем в историю
        for (ClientHandler client : clients) {
            client.sendMessage(message);  // Рассылаем всем
        }
    }

    public CopyOnWriteArrayList<Message> getMessageHistory() {
        return messageHistory;
    }

    public CopyOnWriteArrayList<ClientHandler> getClients() {
        return clients;
    }
}