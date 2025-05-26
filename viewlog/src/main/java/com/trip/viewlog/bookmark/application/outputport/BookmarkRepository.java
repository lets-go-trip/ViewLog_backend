package com.trip.viewlog.bookmark.application.outputport;

import com.trip.viewlog.bookmark.domain.Bookmark;
import com.trip.viewlog.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository {
    Optional<Bookmark> findByUserIdAndAttractionId(Long userId, Long attractionId);
    void deleteByUserIdAndAttractionId(User user, Long attractionId);
	void save(Bookmark bookmark);
	List<Bookmark> findByUser(User user);
}
