package com.trip.viewlog.bookmark.application.outputport;

import com.trip.viewlog.bookmark.domain.Bookmark;
import com.trip.viewlog.user.domain.User;

import java.util.Optional;

public interface BookmarkRepository {
    Optional<Bookmark> findByUserIdAndAttractionId(User user, Long attractionId);
    void deleteByUserIdAndAttractionId(User user, Long attractionId);
	void save(Bookmark bookmark);
}
