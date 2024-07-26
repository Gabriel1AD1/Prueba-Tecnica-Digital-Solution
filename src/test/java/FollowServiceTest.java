import org.example.application.services.FollowServiceImpl;
import org.example.domain.models.User;
import org.example.application.ports.output.UserRepository;
import org.example.infrastructure.adapters.out.repository.InMemoryUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FollowServiceTest {

    @Test
    public void testFollow() {
        UserRepository userRepository = InMemoryUserRepository.getInstance();
        FollowServiceImpl followService = new FollowServiceImpl(userRepository);
        userRepository.save(new User("Alicia"));
        userRepository.save(new User("Ivan"));

        followService.follow("Alicia", "Ivan");
        User user = userRepository.findByUsername("Alicia");

        assertEquals(1, user.getFollowing().size());
        assertEquals("Ivan", user.getFollowing().get(0));
    }
}