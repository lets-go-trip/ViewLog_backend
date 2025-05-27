package com.trip.viewlog.bookmark.controller.response;

import java.time.LocalDateTime;

import com.trip.viewlog.attraction.controller.response.AttractionResponse;
import com.trip.viewlog.bookmark.domain.Bookmark;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookmarkListResponse {
	private final Long id;
	private final AttractionResponse attractionResponse;
	private final LocalDateTime createdAt;
	
	public static BookmarkListResponse from(Bookmark bookmark) {
		return BookmarkListResponse.builder()
				.id(bookmark.getId())
				.attractionResponse(AttractionResponse.from(bookmark.getAttraction()))
				.createdAt(bookmark.getCreatedAt())
				.build();
	}
}
