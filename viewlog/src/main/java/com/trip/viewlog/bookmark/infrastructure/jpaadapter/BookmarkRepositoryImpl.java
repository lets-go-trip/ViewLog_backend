package com.trip.viewlog.bookmark.infrastructure.jpaadapter;

import com.trip.viewlog.bookmark.application.outputport.BookmarkRepository;
import com.trip.viewlog.bookmark.domain.Bookmark;
import com.trip.viewlog.bookmark.infrastructure.jpaadapter.entity.BookmarkEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepository {
    private final BookmarkJpaRepository jpa;

    @Override
    public Optional<Bookmark> findByUserIdAndAttractionId(Long userId, Long attractionId) {
        // UserEntity proxy 로 연결
        return jpa.findByUserEntity_IdAndAttractionEntity_Id(userId, attractionId)
        		.map(BookmarkEntity::toModel);
    }

    @Override
    @Modifying
    @Transactional
    public void save(Bookmark bookmark) {
        BookmarkEntity entity = BookmarkEntity.from(bookmark);
        jpa.save(entity);
    }

    @Override
    @Modifying
    @Transactional
    public void deleteByUserIdAndAttractionId(Long userId, Long attractionId) {
        jpa.deleteByUserEntity_IdAndAttractionEntity_Id(userId, attractionId);
    }

    @Override
    public List<Bookmark> findByUserEntity_Id(Long userId) {
        return jpa.findByUserEntity_Id(userId)
                .stream()
                .map(BookmarkEntity::toModel)
                .toList();
    }

}