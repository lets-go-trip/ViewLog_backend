package com.trip.viewlog.bookmark.application.outputport;

import java.util.List;
import java.util.Optional;

import com.trip.viewlog.bookmark.domain.Bookmark;
import com.trip.viewlog.user.domain.User;

public interface BookmarkRepository {
    Optional<Bookmark> findByUserIdAndAttractionId(User user, Long attractionId);
    void deleteByUserIdAndAttractionId(User user, Long attractionId);
	void save(Bookmark bookmark);
	List<Bookmark> findByUser(User user);
}
