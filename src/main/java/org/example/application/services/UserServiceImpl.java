package org.example.application.services;

import org.example.application.ports.input.UserService;
import org.example.application.ports.output.UserRepository;
import org.example.domain.models.User;

public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public void create(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        repository.save(user);
    }

    @Override
    public boolean userAlreadyExist(String username) {
        return repository.existByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User registerUser(String username, String password) {
        if (repository.existByUsername(username)){
            return null;
        }else {
            return repository.save(new User(username,password));
        }

    }

}
