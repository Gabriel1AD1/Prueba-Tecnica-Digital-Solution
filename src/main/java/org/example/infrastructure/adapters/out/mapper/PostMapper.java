package org.example.infrastructure.adapters.out.mapper;

import org.example.domain.models.Post;
import org.example.infrastructure.adapters.in.dto.PostDTO;
import org.example.infrastructure.adapters.out.annotation.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public class PostMapper {
    public static PostDTO toDTO(Post post) {
        return new PostDTO(post.getMessage(), post.getTimestamp(), post.getUsername());
    }

    public static List<PostDTO> toDTOList(List<Post> posts) {
        return posts.stream().map(PostMapper::toDTO).collect(Collectors.toList());
    }

    public static Post toDomain(PostDTO postDTO) {
        return new Post(postDTO.getMessage(), postDTO.getTimestamp(), postDTO.getUsername());
    }
}
