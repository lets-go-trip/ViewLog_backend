package com.trip.viewlog.post.controller.response;

import com.trip.viewlog.global.domain.File;
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
	private String fileUrl;
	private LocalDateTime createdAt;
	
	public static PostListResponse from(Post post) {
		String thumbnail = post.getPostFiles().stream()
	            .map(File::getFileUrl)
	            .findFirst()
	            .orElse("");           // 파일 없으면 빈 문자열
		
		return PostListResponse.builder()
				.id(post.getId())
	            .title(post.getTitle())
	            .author(post.getAuthor())
	            .fileUrl(thumbnail)   // 첫 번째 URL 또는 빈 문자열
	            .createdAt(post.getCreatedAt())
	            .build();
	}
}
