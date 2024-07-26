import org.example.application.services.PostServiceImpl;
import org.example.domain.models.User;
import org.example.application.ports.output.UserRepository;
import org.example.infrastructure.adapters.out.repository.InMemoryUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PostServiceTest {

    @Test
    public void testPost() {
        UserRepository userRepository = InMemoryUserRepository.getInstance();
        PostServiceImpl postService = new PostServiceImpl(userRepository);
        userRepository.save(new User("Alfonso"));

        postService.post("Alfonso", "Hola Mundo", "10:30");
        User user = userRepository.findByUsername("Alfonso");

        assertEquals(1, user.getPosts().size());
        assertEquals("Hola Mundo", user.getPosts().get(0).getMessage());
    }
}