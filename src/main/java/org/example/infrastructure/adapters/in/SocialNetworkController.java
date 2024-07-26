package org.example.infrastructure.adapters.in;

import org.example.infrastructure.adapters.in.dto.PostDTO;
import org.example.application.ports.input.PostService;
import org.example.application.ports.input.FollowService;
import org.example.application.ports.input.DashboardService;

import java.util.List;
import java.util.Scanner;

public class SocialNetworkController {
    private final PostService postService;
    private final FollowService followService;
    private final DashboardService dashboardService;

    public SocialNetworkController(PostService postService, FollowService followService, DashboardService dashboardService) {
        this.postService = postService;
        this.followService = followService;
        this.dashboardService = dashboardService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("> ");
            String command = scanner.nextLine();
            String[] parts = command.split(" ", 2);
            String action = parts[0];

            switch (action) {
                case "post":
                    handlePost(parts[1]);
                    break;
                case "follow":
                    handleFollow(parts[1]);
                    break;
                case "dashboard":
                    handleDashboard(parts[1]);
                    break;
                default:
                    System.out.println("Unknown command: " + action);
                    break;
            }
        } while (true);
    }

    private void handlePost(String part) {
        String[] parts = part.split(" ", 2);
        String username = parts[0].substring(1);
        String message = parts[1];
        String timestamp = java.time.LocalTime.now().toString();
        postService.post(username, message, timestamp);
        System.out.println(username + " posted -> \"" + message + "\" @" + timestamp);
    }

    private void handleFollow(String part) {
        String[] parts = part.split(" ");
        String follower = parts[0].substring(1);
        String followee = parts[1].substring(1);
        followService.follow(follower, followee);
        System.out.println(follower + " started following " + followee);
    }

    private void handleDashboard(String part) {
        String username = part.substring(1);
        List<PostDTO> dashboard = dashboardService.getDashboard(username);
        System.out.println("Dashboard @" + username);
        for (PostDTO post : dashboard) {
            System.out.println("\"" + post.getMessage() + "\" @" + post.getUsername() + " @" + post.getTimestamp());
        }
    }
}
