package com.trip.viewlog.bookmark.infrastructure.jpaadapter;

import com.trip.viewlog.bookmark.infrastructure.jpaadapter.entity.BookmarkEntity;
import com.trip.viewlog.user.infrastructure.jpaadapter.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkJpaRepository extends JpaRepository<BookmarkEntity, Long> {
    void deleteByUserEntityAndAttractionId(UserEntity userEntity, Long attractionId);
    List<BookmarkEntity> findByUserEntity(UserEntity userEntity);
    Optional<BookmarkEntity> findByUserEntity_IdAndAttractionId(Long userId, Long attractionId);
}
