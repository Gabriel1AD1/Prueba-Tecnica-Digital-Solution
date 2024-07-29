import org.example.application.ports.input.AuthService;
import org.example.application.ports.output.UserRepository;
import org.example.application.services.AuthServiceImpl;
import org.example.domain.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceImplTest {
    private AuthService authService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        authService = new AuthServiceImpl(userRepository);
    }

    @Test
    void login_validCredentials_returnsUser() {
        String username = "testUser";
        String password = "testPass";
        User user = new User(username, password);
        when(userRepository.findByUsername(username)).thenReturn(user);

        User result = authService.login(username, password);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals(password, result.getPassword());
    }

    @Test
    void login_invalidCredentials_returnsNull() {
        String username = "testUser";
        String password = "wrongPass";
        User user = new User(username, "testPass");
        when(userRepository.findByUsername(username)).thenReturn(user);

        User result = authService.login(username, password);

        assertNull(result);
    }

    @Test
    void login_userNotFound_returnsNull() {
        String username = "testUser";
        String password = "testPass";
        when(userRepository.findByUsername(username)).thenReturn(null);

        User result = authService.login(username, password);

        assertNull(result);
    }
}