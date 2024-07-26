package org.example.infrastructure.adapters.out.entity;

import org.example.infrastructure.adapters.out.annotation.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserEntity {
    private String username;
    private List<PostEntity> posts = new ArrayList<>();
    private List<String> following = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(String username, List<PostEntity> posts, List<String> following) {
        this.username = username;
        this.posts = posts != null ? posts : new ArrayList<>();
        this.following = following != null ? following : new ArrayList<>();
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
