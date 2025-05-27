package com.trip.viewlog.bookmark.infrastructure.jpaadapter;

import com.trip.viewlog.bookmark.infrastructure.jpaadapter.entity.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookmarkJpaRepository extends JpaRepository<BookmarkEntity, Long> {
    void deleteByUserEntity_IdAndAttractionEntity_Id(Long userId, Long attractionId);
    @Query("""
            SELECT b 
            FROM BookmarkEntity b
            JOIN FETCH b.userEntity u
            JOIN FETCH b.attractionEntity a
            JOIN FETCH a.contentTypeEntity ct
            WHERE u.id = :userId
            """)
    List<BookmarkEntity> findByUserEntity_Id(Long userId);
    @Query("""
            SELECT b 
            FROM BookmarkEntity b
            JOIN FETCH b.userEntity u
            JOIN FETCH b.attractionEntity a
            JOIN FETCH a.contentTypeEntity ct
            WHERE u.id = :userId
              AND a.id = :attractionId
            """)
    Optional<BookmarkEntity> findByUserEntity_IdAndAttractionEntity_Id(Long userId, Long attractionId);
}
