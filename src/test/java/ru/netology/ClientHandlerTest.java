package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static org.mockito.Mockito.*;

class ClientHandlerTest {

    private ClientHandler clientHandler;
    private Socket socketMock;
    private ChatServer chatServerMock;
    private BufferedReader bufferedReaderMock;
    private PrintWriter printWriterMock;

    @BeforeEach
    void setUp() throws IOException {
        socketMock = Mockito.mock(Socket.class);
        chatServerMock = Mockito.mock(ChatServer.class);
        printWriterMock = Mockito.mock(PrintWriter.class);

        when(socketMock.getInputStream()).thenReturn(Mockito.mock(java.io.InputStream.class));
        when(socketMock.getOutputStream()).thenReturn(Mockito.mock(java.io.OutputStream.class));

        clientHandler = new ClientHandler(socketMock, chatServerMock);
        clientHandler.out = printWriterMock;
    }

    @Test
    void testSendMessage() throws IOException {
        Message message = new Message("2023-01-01T12:00:00", "TestUser", "Hello");

        clientHandler.sendMessage(message);

        verify(printWriterMock, times(1)).println(contains("TestUser"));
        verify(printWriterMock, times(1)).println(contains("Hello"));
    }
}