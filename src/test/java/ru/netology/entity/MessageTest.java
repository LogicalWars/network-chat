package ru.netology.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void testMessageCreation() {
        String dateTime = "2023-01-01T12:00:00";
        String name = "TestUser";
        String messageText = "Hello, world!";

        Message message = new Message(dateTime, name, messageText);

        assertEquals(dateTime, message.getDateTime());
        assertEquals(name, message.getName());
        assertEquals(messageText, message.getMessage());
    }
}