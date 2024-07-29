package org.example;

import org.example.application.ports.input.*;
import org.example.application.services.*;
import org.example.domain.models.Post;
import org.example.infrastructure.configuration.GlobalExceptionHandler;
import org.example.infrastructure.adapters.in.console.controller.SocialNetworkController;
import org.example.domain.models.User;
import org.example.application.ports.output.UserRepository;
import org.example.infrastructure.adapters.out.repository.InMemoryUserRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = InMemoryUserRepository.getInstance();
        PostService postService = new PostServiceImpl(userRepository);
        FollowService followService = new FollowServiceImpl(userRepository);
        AuthService authService = new AuthServiceImpl(userRepository);
        UserService userService = new UserServiceImpl(userRepository);
        DashboardService dashboardService = new DashboardServiceImpl(userRepository);
        GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

        SocialNetworkController socialNetworkController = new SocialNetworkController(
                postService,
                followService,
                userService,
                authService, dashboardService,
                exceptionHandler);

        // Creando usuarios iniciales
        userRepository.save(new User("Alfonso", "1234", List.of(new Post("Primera publicación de Alfonso", "08:00", "Alfonso")), List.of("Ivan", "Alicia")));
        userRepository.save(new User("Ivan", "1234", List.of(new Post("Publicación de Ivan sobre tecnología", "09:30", "Ivan")), List.of("Alfonso", "Alicia", "Carlos", "Daniela", "Elena", "Fernando", "Gabriela", "Hector", "Isabel")));
        userRepository.save(new User("Alicia", "1234", List.of(new Post("Que vida más hermosa", "10:10", "Alicia")), List.of("Ivan", "Alfonso")));
        userRepository.save(new User("Carlos", "1234", List.of(new Post("Disfrutando de un buen libro", "11:00", "Carlos")), List.of("Alfonso", "Alicia")));
        userRepository.save(new User("Daniela", "1234", List.of(new Post("Cocinando algo delicioso", "12:00", "Daniela")), List.of("Ivan", "Carlos")));
        userRepository.save(new User("Elena", "1234", List.of(new Post("Paseando por el parque", "13:00", "Elena")), List.of("Daniela", "Alicia")));
        userRepository.save(new User("Fernando", "1234", List.of(new Post("Aficionado al deporte", "14:00", "Fernando")), List.of("Carlos", "Elena")));
        userRepository.save(new User("Gabriela", "1234", List.of(new Post("Trabajando en un nuevo proyecto", "15:00", "Gabriela")), List.of("Fernando", "Ivan")));
        userRepository.save(new User("Hector", "1234", List.of(new Post("Viaje a la montaña", "16:00", "Hector")), List.of("Gabriela", "Elena")));
        userRepository.save(new User("Isabel", "1234", List.of(new Post("Aprendiendo un nuevo idioma", "17:00", "Isabel")), List.of("Hector", "Fernando")));

        socialNetworkController.run();
    }
}
