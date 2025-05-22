package com.trip.viewlog.post.infrastructure.jpaadapter;

import com.trip.viewlog.post.domain.Post;
import com.trip.viewlog.post.infrastructure.jpaadapter.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> , PagingAndSortingRepository<PostEntity, Long> {
    @EntityGraph(attributePaths = {"userEntity"})
    Page<PostEntity> findAll(Pageable pageable);
}
