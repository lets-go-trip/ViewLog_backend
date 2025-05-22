package com.trip.viewlog.bookmark.infrastructure.jpaadapter;

import com.trip.viewlog.bookmark.infrastructure.jpaadapter.entity.BookmarkEntity;
import com.trip.viewlog.user.infrastructure.jpaadapter.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkJpaRepository extends JpaRepository<BookmarkEntity, Long> {
    Optional<BookmarkEntity> findByUserEntityAndAttractionId(UserEntity userEntity, Long attractionId);
    void deleteByUserEntityAndAttractionId(UserEntity userEntity, Long attractionId);
}
