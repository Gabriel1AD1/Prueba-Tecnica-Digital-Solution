package org.example.infrastructure.adapters.out.mapper;

import org.example.domain.models.Post;
import org.example.domain.models.User;
import org.example.infrastructure.adapters.out.annotation.Mapper;
import org.example.infrastructure.adapters.out.entity.PostEntity;
import org.example.infrastructure.adapters.out.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Mapper
public class UserEntityMapper {

    public static User toEntity(UserEntity entity) {
        User user = new User(entity.getUsername(),entity.getPassword());
        user.setFollowing(entity.getFollowing() != null ? entity.getFollowing() : new ArrayList<>());
        user.setPassword(entity.getPassword());
        List<Post> postDomainList = new ArrayList<>();
        if (entity.getPosts() != null) {
            for (PostEntity post : entity.getPosts()) {
                Post postDomain = new Post(post.getMessage(), post.getTimestamp(), post.getUsername());
                postDomainList.add(postDomain);
            }
        }
        user.setPosts(postDomainList);
        return user;
    }

    public static UserEntity toDomain(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setFollowing(user.getFollowing() != null ? user.getFollowing() : new ArrayList<>());
        userEntity.setPassword(user.getPassword());
        List<PostEntity> postEntityList = new ArrayList<>();
        if (user.getPosts() != null) {
            for (Post post : user.getPosts()) {
                PostEntity postEntity = new PostEntity(post.getMessage(), post.getTimestamp(), post.getUsername());
                postEntityList.add(postEntity);
            }
        }
        userEntity.setPosts(postEntityList);
        return userEntity;
    }
}
