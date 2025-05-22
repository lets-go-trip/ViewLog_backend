package com.trip.viewlog.post.controller.response;

import com.trip.viewlog.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostDetailResponse {
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    
	public static PostDetailResponse from(Post post) {
		return PostDetailResponse.builder()
		.author(post.getAuthor())
		.content(post.getContent())
		.createdAt(post.getCreatedAt())
		.title(post.getTitle())
		.build();
	}
}
