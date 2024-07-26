package org.example;

import org.example.infrastructure.adapters.in.SocialNetworkController;
import org.example.application.services.SocialNetworkService;
import org.example.domain.models.User;
import org.example.domain.ports.output.UserRepository;
import org.example.infrastructure.adapters.out.repository.InMemoryUserRepository;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new InMemoryUserRepository();
        SocialNetworkService socialNetworkService = new SocialNetworkService(userRepository);
        SocialNetworkController socialNetworkController = new SocialNetworkController(socialNetworkService, socialNetworkService, socialNetworkService);

        // Creando usuarios iniciales
        userRepository.save(new User("Alfonso"));
        userRepository.save(new User("Ivan"));
        userRepository.save(new User("Alicia"));

        socialNetworkController.run();
    }
}
