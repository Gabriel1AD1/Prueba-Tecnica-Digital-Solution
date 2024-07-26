package org.example.infrastructure.adapters.out.entity;

import org.example.infrastructure.adapters.out.annotation.Entity;

@Entity
public class PostEntity {
    private  String message;
    private  String timestamp;
    private  String username;

    public PostEntity() {
    }


    public PostEntity(String message, String timestamp, String username) {
        this.message = message;
        this.timestamp = timestamp;
        this.username = username;
    }

    /*Getters and Setters*/

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
