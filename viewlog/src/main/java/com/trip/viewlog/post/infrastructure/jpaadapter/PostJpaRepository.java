package com.trip.viewlog.post.infrastructure.jpaadapter;

import com.trip.viewlog.post.infrastructure.jpaadapter.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> , PagingAndSortingRepository<PostEntity, Long> {
}
