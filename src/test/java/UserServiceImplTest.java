
import org.example.application.ports.input.UserService;
import org.example.application.ports.output.UserRepository;
import org.example.application.services.UserServiceImpl;
import org.example.domain.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void create_newUser_savesUser() {
        String username = "newUser";
        String password = "newPass";
        User user = new User(username, password);

        userService.create(username, password);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void userAlreadyExist_existingUser_returnsTrue() {
        String username = "existingUser";
        when(userRepository.existByUsername(username)).thenReturn(true);

        boolean result = userService.userAlreadyExist(username);

        assertTrue(result);
    }

    @Test
    void userAlreadyExist_nonExistingUser_returnsFalse() {
        String username = "nonExistingUser";
        when(userRepository.existByUsername(username)).thenReturn(false);

        boolean result = userService.userAlreadyExist(username);

        assertFalse(result);
    }

    @Test
    void findByUsername_existingUser_returnsUser() {
        String username = "testUser";
        User user = new User(username, "testPass");
        when(userRepository.findByUsername(username)).thenReturn(user);

        User result = userService.findByUsername(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }

    @Test
    void findByUsername_nonExistingUser_returnsNull() {
        String username = "testUser";
        when(userRepository.findByUsername(username)).thenReturn(null);

        User result = userService.findByUsername(username);

        assertNull(result);
    }

    @Test
    void registerUser_newUser_savesAndReturnsUser() {
        String username = "newUser";
        String password = "newPass";
        when(userRepository.existByUsername(username)).thenReturn(false);
        User user = new User(username, password);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.registerUser(username, password);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void registerUser_existingUser_returnsNull() {
        String username = "existingUser";
        String password = "existingPass";
        when(userRepository.existByUsername(username)).thenReturn(true);

        User result = userService.registerUser(username, password);

        assertNull(result);
        verify(userRepository, times(0)).save(any(User.class));
    }
}
