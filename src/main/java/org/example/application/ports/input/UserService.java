package org.example.application.ports.input;

import org.example.domain.models.User;

public interface UserService {
    void create(String username , String password);

    boolean userAlreadyExist(String username);

    User findByUsername(String username);

    User registerUser(String username, String password);
}
