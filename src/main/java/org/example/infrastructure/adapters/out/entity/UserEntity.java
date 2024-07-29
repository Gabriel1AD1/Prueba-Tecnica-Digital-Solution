package org.example.infrastructure.adapters.out.entity;

import org.example.infrastructure.adapters.out.annotation.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {
    private String username;

    private String password;
    private List<PostEntity> posts = new ArrayList<>();
    private List<String> following = new ArrayList<>();

    public UserEntity() {
        this.posts = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public UserEntity(String username, String password ,List<PostEntity> posts, List<String> following) {
        this.password = password;
        this.username = username;
        this.posts = posts != null ? posts : new ArrayList<>();
        this.following = following != null ? following : new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts != null ? posts : new ArrayList<>();
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following != null ? following : new ArrayList<>();
    }
}
