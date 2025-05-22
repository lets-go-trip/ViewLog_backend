package com.trip.viewlog.bookmark.application;

import com.trip.viewlog.bookmark.application.outputport.BookmarkRepository;
import com.trip.viewlog.bookmark.controller.inputport.BookmarkService;
import com.trip.viewlog.bookmark.domain.Bookmark;
import com.trip.viewlog.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    @Override
    public boolean exists(User user, Long attractionId) {
        return bookmarkRepository
                .findByUserIdAndAttractionId(user, attractionId)
                .isPresent();
    }

    @Override
    public void add(User user, Long attractionId) {
        // 중복 방지 체크
        if (!exists(user, attractionId)) {
            Bookmark bookmark = Bookmark.builder()
                    .user(user)
                    .attractionId(attractionId)
                    .build();
            bookmarkRepository.save(bookmark);
        }
    }

    @Override
    public void remove(User user, Long attractionId) {
        if (exists(user, attractionId)) {
            bookmarkRepository.deleteByUserIdAndAttractionId(user, attractionId);
        }
    }
}