package com.trip.viewlog.post.domain;

import com.trip.viewlog.global.domain.File;
import com.trip.viewlog.post.controller.request.CreatePostRequest;
import com.trip.viewlog.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class Post {
    private final Long id;
    private final User user;
    private final String title;
    private final String content;
    private final String author;
    private final List<File> postFiles;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    
    public static Post create(CreatePostRequest dto, User user) {
    	return Post.builder()
    			.user(user)
    			.title(dto.getTitle())
    			.content(dto.getContent())
    			.author(user.getName())
    			.postFiles(new ArrayList<>())
    			.build();
    }

    public Post update(CreatePostRequest dto) {
        return Post.builder()
                .id(id)
                .user(user)
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(author)
                .postFiles(new ArrayList<>())
                .build();
    }
    
    public void addFile(File file) {
        postFiles.add(file);
    }
}
