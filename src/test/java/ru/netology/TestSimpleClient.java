package ru.netology;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class TestSimpleClient {
    private static final Integer PORT = 8080;

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", PORT);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println("Привет чат!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
