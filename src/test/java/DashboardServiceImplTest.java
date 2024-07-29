
import org.example.application.ports.input.DashboardService;
import org.example.application.ports.output.UserRepository;
import org.example.application.services.DashboardServiceImpl;
import org.example.domain.models.Post;
import org.example.domain.models.User;
import org.example.domain.models.dto.PostDTO;
import org.example.infrastructure.adapters.out.mapper.PostMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardServiceImplTest {
    private UserRepository userRepository;
    private DashboardService dashboardService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        dashboardService = new DashboardServiceImpl(userRepository);
    }

    @Test
    void getDashboard_userWithFollowers_returnsPosts() {
        String username = "testUser";
        User user = new User(username, "password");
        user.follow("followee1");
        user.follow("followee2");

        List<Post> followee1Posts = List.of(new Post("Message from followee1", "08:00", "followee1"));
        List<Post> followee2Posts = List.of(new Post("Message from followee2", "09:00", "followee2"));

        when(userRepository.findByUsername(username)).thenReturn(user);
        when(userRepository.findByUsername("followee1")).thenReturn(new User("followee1", "password", followee1Posts, new ArrayList<>()));
        when(userRepository.findByUsername("followee2")).thenReturn(new User("followee2", "password", followee2Posts, new ArrayList<>()));

        List<PostDTO> dashboard = dashboardService.getDashboard(username);

        assertEquals(2, dashboard.size());
        assertEquals("Message from followee2", dashboard.get(0).getMessage());
        assertEquals("Message from followee1", dashboard.get(1).getMessage());
    }

    @Test
    void getDashboard_userWithoutFollowers_returnsEmptyList() {
        String username = "testUser";
        User user = new User(username, "password");

        when(userRepository.findByUsername(username)).thenReturn(user);

        List<PostDTO> dashboard = dashboardService.getDashboard(username);

        assertTrue(dashboard.isEmpty());
    }

    @Test
    void getDashboard_nonExistentUser_throwsException() {
        String username = "testUser";

        when(userRepository.findByUsername(username)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            dashboardService.getDashboard(username);
        });
    }
}
