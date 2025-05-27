package com.trip.viewlog.bookmark.application.outputport;

import com.trip.viewlog.bookmark.domain.Bookmark;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository {
    Optional<Bookmark> findByUserIdAndAttractionId(Long userId, Long attractionId);
    void deleteByUserIdAndAttractionId(Long userId, Long attractionId);
	void save(Bookmark bookmark);
	List<Bookmark> findByUserEntity_Id(Long userId);
}
