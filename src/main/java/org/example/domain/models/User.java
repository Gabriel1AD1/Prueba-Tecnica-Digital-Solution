package org.example.domain.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private List<Post> posts;
    private List<String> following;

    public User(String username) {
        this.username = username;
        this.posts = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<Post> getPosts() {
        return new ArrayList<>(posts);
    }

    public List<String> getFollowing() {
        return new ArrayList<>(following);
    }

    public void addPost(String message, String timestamp) {
        posts.add(new Post(message, timestamp, username));
    }

    public void follow(String userToFollow) {
        if (!following.contains(userToFollow)) {
            following.add(userToFollow);
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }
}
