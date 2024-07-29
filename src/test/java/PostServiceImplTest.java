
import org.example.application.ports.input.PostService;
import org.example.application.ports.output.UserRepository;
import org.example.application.services.PostServiceImpl;
import org.example.domain.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceImplTest {
    private UserRepository userRepository;
    private PostService postService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        postService = new PostServiceImpl(userRepository);
    }

    @Test
    void post_validUser_addsPost() {
        String username = "testUser";
        String message = "This is a test post";
        String timestamp = "10:00";

        User user = new User(username, "password");

        when(userRepository.findByUsername(username)).thenReturn(user);

        postService.post(username, message, timestamp);

        assertEquals(1, user.getPosts().size());
        assertEquals(message, user.getPosts().get(0).getMessage());
        assertEquals(timestamp, user.getPosts().get(0).getTimestamp());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void post_userNotFound_throwsException() {
        String username = "testUser";
        String message = "This is a test post";
        String timestamp = "10:00";

        when(userRepository.findByUsername(username)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            postService.post(username, message, timestamp);
        });
    }
}
