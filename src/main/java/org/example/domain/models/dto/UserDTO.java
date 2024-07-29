package org.example.domain.models.dto;

import java.util.List;

public class UserDTO {
    private String username;
    private List<PostDTO> posts;
    private List<String> following;

    public UserDTO(String username, List<PostDTO> posts, List<String> following) {
        this.username = username;
        this.posts = posts;
        this.following = following;
    }

    public String getUsername() {
        return username;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }
}
