package org.example.application.services;

import org.example.application.ports.input.DashboardService;
import org.example.domain.models.Post;
import org.example.domain.models.User;
import org.example.application.ports.output.UserRepository;
import org.example.domain.models.dto.PostDTO;
import org.example.infrastructure.adapters.out.mapper.PostMapper;

import java.util.ArrayList;
import java.util.List;

public class DashboardServiceImpl implements DashboardService {
    private final UserRepository userRepository;

    public DashboardServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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