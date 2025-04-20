package ru.netology.util;

import ru.netology.entity.Message;

public interface Logger {
    void log(String level, Message message);
}
