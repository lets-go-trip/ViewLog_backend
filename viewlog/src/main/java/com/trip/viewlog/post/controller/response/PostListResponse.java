package com.trip.viewlog.post.controller.response;

import com.trip.viewlog.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class PostListResponse {
	private Long id;
	private String title;
	private String author;
	private LocalDateTime createdAt;
	
	public static PostListResponse from(Post post) {
		return PostListResponse.builder()
				.id(post.getId())
				.title(post.getTitle())
				.author(post.getAuthor())
				.createdAt(post.getCreatedAt())
				.build();
	}
}
