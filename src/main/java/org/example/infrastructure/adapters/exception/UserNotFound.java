package org.example.infrastructure.adapters.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String message) {
        System.out.println(message);
    }


}