package org.example.infrastructure.adapters.in.dto;

public class PostDTO {
    private String message;
    private String timestamp;
    private String username;

    public PostDTO(String message, String timestamp, String username) {
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

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
