package org.example.infrastructure.adapters.in.console.controller;

import org.example.application.ports.input.*;
import org.example.domain.models.User;
import org.example.infrastructure.configuration.GlobalExceptionHandler;
import org.example.domain.models.dto.PostDTO;

import java.util.List;
import java.util.Scanner;

public class SocialNetworkController {
    private final PostService postService;
    private final FollowService followService;
    private final UserService userService;

    private final AuthService authService;
    private final DashboardService dashboardService;
    private final GlobalExceptionHandler exceptionHandler;

    /*
     * State controller
     * */
    private User loggedInUser;
    private boolean isRunning = true;

    public SocialNetworkController(PostService postService, FollowService followService, UserService userService, AuthService authService, DashboardService dashboardService, GlobalExceptionHandler exceptionHandler) {
        this.postService = postService;
        this.followService = followService;
        this.userService = userService;
        this.authService = authService;
        this.dashboardService = dashboardService;
        this.exceptionHandler = exceptionHandler;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            if (loggedInUser == null) {
                System.out.print("> ");
                System.out.println("1 -> Login");
                System.out.print("> ");
                System.out.println("2 -> Register");
                System.out.print("> ");
                System.out.println("3 -> Shutdown App");
                String commandInitial = scanner.nextLine();

                switch (commandInitial) {
                    case "1":
                        System.out.print("Username: ");
                        String loginUsername = scanner.nextLine();
                        System.out.print("Password: ");
                        String loginPassword = scanner.nextLine();
                        handleLogin(loginUsername, loginPassword);
                        break;
                    case "2":
                        System.out.print("Username: ");
                        String registerUsername = scanner.nextLine();
                        System.out.print("Password: ");
                        String registerPassword = scanner.nextLine();
                        handleRegisterUser(registerUsername, registerPassword);
                        break;
                    case "3":
                        handleShutdown();
                        break;
                    default:
                        System.out.println("Unknown command: " + commandInitial);
                        break;
                }
            } else {
                System.out.print("> ");
                String command = scanner.nextLine();
                String[] parts = command.split(" ", 2);
                String action = parts[0];

                try {
                    switch (action) {
                        case "post":
                            handlePost(parts.length > 1 ? parts[1] : "");
                            break;
                        case "follow":
                            handleFollow(parts.length > 1 ? parts[1] : "");
                            break;
                        case "dashboard":
                            handleDashboard();
                            break;
                        case "logout":
                            handleLogout();
                            break;
                        default:
                            System.out.println("Unknown command: " + action);
                            break;
                    }
                } catch (Exception e) {
                    exceptionHandler.handle(e);
                }
            }
        }
    }

    private void handleLogin(String username, String password) {
        try {
            authService.login(username,password);
            if (authService.login(username, password) == null){
                System.out.println("Username or password incorrect");
            }else {
                loggedInUser = authService.login(username,password);
                System.out.println("Hello " + username);

            }
        } catch (Exception e) {
            exceptionHandler.handle(e);
        }
    }

    private void handleRegisterUser(String username, String password) {
        try {
            User userRegister = userService.registerUser(username, password);
            if (userRegister == null) {
                System.out.println("Username already exists");
            } else {
                System.out.println("Signup successful");
                loggedInUser = userRegister;
            }
        } catch (Exception e) {
            exceptionHandler.handle(e);
        }
    }

    private void handlePost(String part) {
        try {
            if (loggedInUser == null) {
                System.out.println("You need to be logged in to post.");
                return;
            }

            String message = part;
            String username = loggedInUser.getUsername();
            String timestamp = java.time.LocalTime.now().toString();
            postService.post(username, message, timestamp);
            System.out.println(username + " posted -> \"" + message + "\" @" + timestamp);
        } catch (Exception e) {
            exceptionHandler.handle(e);
        }
    }

    private void handleFollow(String part) {
        try {
            if (loggedInUser == null) {
                System.out.println("You need to be logged in to follow someone.");
                return;
            }

            String followee = part.substring(1);
            String follower = loggedInUser.getUsername();
            User user = userService.findByUsername(followee);
            followService.follow(follower, user.getUsername());
            System.out.println(follower + " started following " + followee);
        } catch (Exception e) {
            exceptionHandler.handle(e);
        }
    }

    private void handleDashboard() {
        try {
            if (loggedInUser == null) {
                System.out.println("You need to be logged in to view the dashboard.");
                return;
            }

            String username = loggedInUser.getUsername();
            List<PostDTO> dashboard = dashboardService.getDashboard(username);
            System.out.println("Dashboard @" + username);
            for (PostDTO post : dashboard) {
                System.out.println("\"" + post.getMessage() + "\" @" + post.getUsername() + " @" + post.getTimestamp());
            }
        } catch (Exception e) {
            exceptionHandler.handle(e);
        }
    }

    private void handleLogout() {
        System.out.println("Goodbye " + loggedInUser.getUsername());
        loggedInUser = null;
    }

    private void handleShutdown() {
        System.out.println("Shutting down the application...");
        isRunning = false;
    }
}
