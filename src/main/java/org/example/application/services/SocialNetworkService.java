package org.example.application.services;

import org.example.domain.models.Post;
import org.example.domain.models.User;
import org.example.application.ports.input.PostService;
import org.example.application.ports.input.FollowService;
import org.example.application.ports.input.DashboardService;
import org.example.domain.ports.output.UserRepository;
import org.example.infrastructure.adapters.in.dto.PostDTO;
import org.example.infrastructure.adapters.out.mapper.PostMapper;

import java.util.ArrayList;
import java.util.List;

public class SocialNetworkService implements PostService, FollowService, DashboardService {
    private final UserRepository userRepository;

    public SocialNetworkService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void post(String username, String message, String timestamp) {
        User user = userRepository.findByUsername(username);
        user.addPost(message, timestamp);
        userRepository.save(user);
    }

    @Override
    public void follow(String follower, String followee) {
        User user = userRepository.findByUsername(follower);
        user.follow(followee);
        userRepository.save(user);
    }

    @Override
    public List<PostDTO> getDashboard(String username) {
        User user = userRepository.findByUsername(username);
        List<Post> dashboardPosts = new ArrayList<>();
        for (String followee : user.getFollowing()) {
            User followeeUser = userRepository.findByUsername(followee);
            dashboardPosts.addAll(followeeUser.getPosts());
        }
        dashboardPosts.sort((p1, p2) -> p2.getTimestamp().compareTo(p1.getTimestamp()));

        return PostMapper.toDTOList(dashboardPosts);
    }
}
