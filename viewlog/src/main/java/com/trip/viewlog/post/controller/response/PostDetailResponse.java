package com.trip.viewlog.post.controller.response;

import com.trip.viewlog.global.domain.File;
import com.trip.viewlog.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class PostDetailResponse {
	private Long userId;
    private String title;
    private String content;
    private String author;
    private List<String> fileUrls;
    private LocalDateTime createdAt;
    
	public static PostDetailResponse from(Post post) {
		return PostDetailResponse.builder()
				.userId(post.getUser().getId())
				.author(post.getAuthor())
				.content(post.getContent())
				.fileUrls(post.getPostFiles().stream().map(File::getFileUrl).collect(Collectors.toList()))
				.createdAt(post.getCreatedAt())
				.title(post.getTitle())
				.build();
	}
}
