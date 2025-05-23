package com.trip.viewlog.post.domain;

import com.trip.viewlog.post.controller.request.CreatePostRequest;
import com.trip.viewlog.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Post {
    private final Long id;
    private final User user;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Post update(CreatePostRequest dto) {
        return Post.builder()
                .id(id)
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(author)
                .build();
    }
}
