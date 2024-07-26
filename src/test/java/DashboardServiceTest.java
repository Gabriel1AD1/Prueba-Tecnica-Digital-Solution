import org.example.application.services.DashboardServiceImpl;
import org.example.application.services.FollowServiceImpl;
import org.example.domain.models.User;
import org.example.domain.ports.output.UserRepository;
import org.example.infrastructure.adapters.in.dto.PostDTO;
import org.example.infrastructure.adapters.out.repository.InMemoryUserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DashboardServiceTest {

    @Test
    public void testGetDashboard() {
        UserRepository userRepository = new InMemoryUserRepository();
        DashboardServiceImpl dashboardService = new DashboardServiceImpl(userRepository);
        userRepository.save(new User("Alicia"));
        User ivan = new User("Ivan");
        ivan.addPost("Hoy puede ser un gran dia", "08:10");
        userRepository.save(ivan);

        FollowServiceImpl followService = new FollowServiceImpl(userRepository);
        followService.follow("Alicia", "Ivan");

        List<PostDTO> dashboard = dashboardService.getDashboard("Alicia");

        assertEquals(1, dashboard.size());
        assertEquals("Hoy puede ser un gran dia", dashboard.get(0).getMessage());
    }
}