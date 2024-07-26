package org.example.application.services;

import org.example.application.ports.input.PostService;
import org.example.domain.models.User;
import org.example.domain.ports.output.UserRepository;

public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;

    public PostServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void post(String username, String message, String timestamp) {
        User user = userRepository.findByUsername(username);
        user.addPost(message, timestamp);
        userRepository.save(user);
    }
}