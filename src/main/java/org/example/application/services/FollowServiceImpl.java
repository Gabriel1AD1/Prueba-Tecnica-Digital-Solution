package org.example.application.services;

import org.example.application.ports.input.FollowService;
import org.example.domain.models.User;
import org.example.domain.ports.output.UserRepository;

public class FollowServiceImpl implements FollowService {
    private final UserRepository userRepository;

    public FollowServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void follow(String follower, String followee) {
        User user = userRepository.findByUsername(follower);
        user.follow(followee);
        userRepository.save(user);
    }
}
