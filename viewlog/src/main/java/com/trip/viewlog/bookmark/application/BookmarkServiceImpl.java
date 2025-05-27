package com.trip.viewlog.bookmark.application;

import com.trip.viewlog.attraction.application.outputport.AttractionRepository;
import com.trip.viewlog.attraction.domain.Attraction;
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
    private final AttractionRepository attractionRepository;
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
        	Attraction a = attractionRepository.findById(attractionId)
        			.orElse(null);
            Bookmark bookmark = Bookmark.builder()
                    .user(u)
                    .attraction(a)
                    .build();
            bookmarkRepository.save(bookmark);
        }
    }

    @Override
    public void remove(Long userId, Long attractionId) {
            bookmarkRepository.deleteByUserIdAndAttractionId(userId,attractionId);
    }
    
    @Override
    public List<Bookmark> getAllByUser(Long userId) {
            return bookmarkRepository.findByUserEntity_Id(userId);
    }
}