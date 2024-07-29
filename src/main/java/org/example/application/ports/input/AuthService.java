package org.example.application.ports.input;

import org.example.domain.models.User;

public interface AuthService {
    User login(String username, String password);
}
