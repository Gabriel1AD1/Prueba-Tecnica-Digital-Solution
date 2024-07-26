package org.example;

import org.example.application.ports.input.DashboardService;
import org.example.application.ports.input.FollowService;
import org.example.application.ports.input.PostService;
import org.example.application.services.DashboardServiceImpl;
import org.example.application.services.FollowServiceImpl;
import org.example.application.services.PostServiceImpl;
import org.example.infrastructure.adapters.configuration.GlobalExceptionHandler;
import org.example.infrastructure.adapters.in.controller.SocialNetworkController;
import org.example.domain.models.User;
import org.example.application.ports.output.UserRepository;
import org.example.infrastructure.adapters.out.repository.InMemoryUserRepository;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = InMemoryUserRepository.getInstance();
        PostService postService = new PostServiceImpl(userRepository);
        FollowService followService = new FollowServiceImpl(userRepository);
        DashboardService dashboardService = new DashboardServiceImpl(userRepository);
        GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

        SocialNetworkController socialNetworkController = new SocialNetworkController(
                postService,
                followService,
                dashboardService,
                exceptionHandler);

        // Creando usuarios iniciales
        userRepository.save(new User("Alfonso"));
        userRepository.save(new User("Ivan"));
        userRepository.save(new User("Alicia"));

        socialNetworkController.run();
    }
}
