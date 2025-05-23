package com.trip.viewlog.bookmark.domain;

import com.trip.viewlog.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Bookmark {
	private final Long id;
	private final User user;
	private final Long attractionId;
	private final LocalDateTime createdAt;
}
