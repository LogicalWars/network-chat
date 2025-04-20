package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Message;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatServerTest {

    private ChatServer chatServer;
    private ClientHandler clientHandlerMock;
    private Message testMessage;

    @BeforeEach
    void setUp() {
        chatServer = new ChatServer();
        clientHandlerMock = Mockito.mock(ClientHandler.class);
        testMessage = new Message("2023-01-01T12:00:00", "TestUser", "Hello");
    }

    @Test
    void testAddClient() {
        chatServer.addClient(clientHandlerMock);
        assertEquals(1, chatServer.getClients().size());
    }

    @Test
    void testRemoveClient() {
        chatServer.addClient(clientHandlerMock);
        chatServer.removeClient(clientHandlerMock);
        assertEquals(0, chatServer.getMessageHistory().size());
    }

    @Test
    void testBroadcastMessage() {
        ClientHandler anotherClientMock = Mockito.mock(ClientHandler.class);
        chatServer.addClient(clientHandlerMock);
        chatServer.addClient(anotherClientMock);

        chatServer.broadcastMessage(testMessage);

        verify(clientHandlerMock, times(1)).sendMessage(testMessage);
        verify(anotherClientMock, times(1)).sendMessage(testMessage);
        assertEquals(1, chatServer.getMessageHistory().size());
        assertEquals(testMessage, chatServer.getMessageHistory().get(0));
    }
}