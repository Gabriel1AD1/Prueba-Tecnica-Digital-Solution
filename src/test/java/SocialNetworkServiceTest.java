package org.example.application.services;

import org.example.domain.models.User;
import org.example.domain.ports.output.UserRepository;
import org.example.infrastructure.adapters.out.repository.InMemoryUserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SocialNetworkServiceTest {

    @Test
    public void testPost() {
        UserRepository userRepository = new InMemoryUserRepository();
        SocialNetworkService socialNetworkService = new SocialNetworkService(userRepository);
        userRepository.save(new User("Alfonso"));

        socialNetworkService.post("Alfonso", "Hola Mundo", "10:30");
        User user = userRepository.findByUsername("Alfonso");

        assertEquals(1, user.getPosts().size());
        assertEquals("Hola Mundo", user.getPosts().get(0).getMessage());
    }

    @Test
    public void testFollow() {
        UserRepository userRepository = new InMemoryUserRepository();
        SocialNetworkService socialNetworkService = new SocialNetworkService(userRepository);
        userRepository.save(new User("Alicia"));
        userRepository.save(new User("Ivan"));

        socialNetworkService.follow("Alicia", "Ivan");
        User user = userRepository.findByUsername("Alicia");

        assertEquals(1, user.getFollowing().size());
        assertEquals("Ivan", user.getFollowing().get(0));
    }

    @Test
    public void testGetDashboard() {
        UserRepository userRepository = new InMemoryUserRepository();
        SocialNetworkService socialNetworkService = new SocialNetworkService(userRepository);
        userRepository.save(new User("Alicia"));
        User ivan = new User("Ivan");
        ivan.addPost("Hoy puede ser un gran dia", "08:10");
        userRepository.save(ivan);

        socialNetworkService.follow("Alicia", "Ivan");
        List<org.example.infrastructure.adapters.in.dto.PostDTO> dashboard = socialNetworkService.getDashboard("Alicia");

        assertEquals(1, dashboard.size());
        assertEquals("Hoy puede ser un gran dia", dashboard.get(0).getMessage());
    }
}
