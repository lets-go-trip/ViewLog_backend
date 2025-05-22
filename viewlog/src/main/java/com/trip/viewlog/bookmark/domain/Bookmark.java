package com.trip.viewlog.bookmark.domain;

import com.trip.viewlog.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Bookmark {
	private Long id;
	private User user;
	private Long attractionId;
	private LocalDateTime createdAt;
}
