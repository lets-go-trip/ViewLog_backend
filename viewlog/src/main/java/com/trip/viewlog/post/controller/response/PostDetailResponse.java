package com.trip.viewlog.post.controller.response;

import com.trip.viewlog.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostDetailResponse {
	private Long userId;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    
	public static PostDetailResponse from(Post post) {
		return PostDetailResponse.builder()
				.userId(post.getUser().getId())
				.author(post.getAuthor())
				.content(post.getContent())
				.createdAt(post.getCreatedAt())
				.title(post.getTitle())
				.build();
	}
}
