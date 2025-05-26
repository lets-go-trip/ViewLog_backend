package com.trip.viewlog.bookmark.application;

import com.trip.viewlog.bookmark.application.outputport.BookmarkRepository;
import com.trip.viewlog.bookmark.controller.inputport.BookmarkService;
import com.trip.viewlog.bookmark.domain.Bookmark;
import com.trip.viewlog.user.application.outputport.UserRepository;
import com.trip.viewlog.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;

    @Override
    public boolean exists(Long userId, Long attractionId) {
        return bookmarkRepository
                .findByUserIdAndAttractionId(userId, attractionId)
                .isPresent();
    }

    @Override
    public void add(Long userId, Long attractionId) {
        // 중복 방지 체크
        if (!exists(userId, attractionId)) {
        	User u = userRepository.findById(userId)
        		    .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));
        	
            Bookmark bookmark = Bookmark.builder()
                    .user(u)
                    .attractionId(attractionId)
                    .build();
            bookmarkRepository.save(bookmark);
        }
    }

    @Override
    public void remove(User user, Long attractionId) {
            bookmarkRepository.deleteByUserIdAndAttractionId(user, attractionId);
    }
    
    @Override
    public List<Bookmark> getAllByUser(User user) {
            return bookmarkRepository.findByUser(user);
    }
}