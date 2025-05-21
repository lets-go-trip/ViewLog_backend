package com.trip.viewlog.post.infrastructure.jpaadapter;

import com.trip.viewlog.post.infrastructure.jpaadapter.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {
	List<PostEntity> findAllByOrderByCreatedAtDesc();
	
	
}
