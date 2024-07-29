package org.example.infrastructure.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String message) {
        System.out.println(message);
    }


}