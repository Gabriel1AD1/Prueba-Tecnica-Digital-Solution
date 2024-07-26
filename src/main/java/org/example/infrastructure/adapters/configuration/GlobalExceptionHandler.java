package org.example.infrastructure.adapters.configuration;

import org.example.infrastructure.adapters.exception.SocialNetworkException;

public class GlobalExceptionHandler {

    public void handle(Exception e) {
        if (e instanceof SocialNetworkException) {
            System.err.println("Error: " + e.getMessage());
        } else {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}