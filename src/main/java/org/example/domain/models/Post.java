package org.example.domain.models;

public class Post {
    private String message;
    private String timestamp;
    private String username;

    public Post() {
    }

    public Post(String message, String timestamp, String username) {
        this.message = message;
        this.timestamp = timestamp;
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }


}
