package ru.netology.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    private String dateTime;
    private String name;
    private String message;

    public Message(
            @JsonProperty("dateTime") String dateTime,
            @JsonProperty("name") String name,
            @JsonProperty("message") String message

    ) {
        this.dateTime = dateTime;
        this.name = name;
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
