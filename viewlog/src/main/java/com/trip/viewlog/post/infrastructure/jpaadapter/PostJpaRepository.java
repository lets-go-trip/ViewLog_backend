package com.trip.viewlog.post.infrastructure.jpaadapter;

import com.trip.viewlog.post.infrastructure.jpaadapter.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> , PagingAndSortingRepository<PostEntity, Long> {
    @EntityGraph(attributePaths = {"userEntity"})
    Page<PostEntity> findAll(Pageable pageable);
    int deleteByUserEntity_IdAndId(Long userId, Long postId);
    @EntityGraph(attributePaths = {"userEntity"})
	List<PostEntity> findByUserEntity_Id(Long userId);
}
