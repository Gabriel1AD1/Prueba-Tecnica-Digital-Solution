package org.example.infrastructure.configuration;

import org.example.infrastructure.exception.SocialNetworkException;

public class GlobalExceptionHandler {

    public void handle(Exception e) {
        if (e instanceof SocialNetworkException) {
            System.err.println("Error: " + e.getMessage());
        } else {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}