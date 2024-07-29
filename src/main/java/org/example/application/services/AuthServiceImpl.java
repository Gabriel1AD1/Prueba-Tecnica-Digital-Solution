package org.example.application.services;

import org.example.application.ports.input.AuthService;
import org.example.application.ports.input.UserService;
import org.example.application.ports.output.UserRepository;
import org.example.domain.models.User;

public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;

    public AuthServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User login(String username, String password) {

        User userFindByUsername = repository.findByUsername(username);
        if (userFindByUsername != null && userFindByUsername.getPassword().equals(password)) {
            return userFindByUsername;
        } else {
            return  null;
        }

    }
}
