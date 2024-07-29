
import org.example.application.ports.input.FollowService;
import org.example.application.ports.output.UserRepository;
import org.example.application.services.FollowServiceImpl;
import org.example.domain.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FollowServiceImplTest {
    private UserRepository userRepository;
    private FollowService followService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        followService = new FollowServiceImpl(userRepository);
    }

    @Test
    void follow_validUsers_followerStartsFollowingFollowee() {
        String followerUsername = "follower";
        String followeeUsername = "followee";

        User follower = new User(followerUsername, "password");
        User followee = new User(followeeUsername, "password");

        when(userRepository.findByUsername(followerUsername)).thenReturn(follower);
        when(userRepository.findByUsername(followeeUsername)).thenReturn(followee);

        followService.follow(followerUsername, followeeUsername);

        assertTrue(follower.getFollowing().contains(followeeUsername));
        verify(userRepository, times(1)).save(follower);
    }

    @Test
    void follow_followerAlreadyFollowingFollowee_noDuplicateFollowing() {
        String followerUsername = "follower";
        String followeeUsername = "followee";

        User follower = new User(followerUsername, "password");
        follower.follow(followeeUsername);

        when(userRepository.findByUsername(followerUsername)).thenReturn(follower);
        when(userRepository.findByUsername(followeeUsername)).thenReturn(new User(followeeUsername, "password"));

        followService.follow(followerUsername, followeeUsername);

        assertEquals(1, follower.getFollowing().size());
        assertTrue(follower.getFollowing().contains(followeeUsername));
        verify(userRepository, times(1)).save(follower);
    }

    @Test
    void follow_followerDoesNotExist_throwsException() {
        String followerUsername = "follower";
        String followeeUsername = "followee";

        when(userRepository.findByUsername(followerUsername)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> {
            followService.follow(followerUsername, followeeUsername);
        });
    }

}
