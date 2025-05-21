package com.trip.viewlog.post.infrastructure.jpaadapter;

import com.trip.viewlog.post.application.outputport.PostRepository;
import com.trip.viewlog.post.domain.Post;
import com.trip.viewlog.post.infrastructure.jpaadapter.entity.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository{
	
	private final PostJpaRepository postJpaRepository;
	
	@Override
	public List<Post> findAllByOrderByCreatedAtDesc() {
		return postJpaRepository.findAllByOrderByCreatedAtDesc()
				.stream()
				.map(PostEntity::toModel)
				.toList();
	}
	
	@Override
	public Optional<Post> findById(Long id) {
		return postJpaRepository.findById(id).map(PostEntity::toModel);
	}

}
