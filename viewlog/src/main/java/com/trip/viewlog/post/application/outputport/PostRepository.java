package com.trip.viewlog.post.application.outputport;

import com.trip.viewlog.post.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
	List<Post> findAllByOrderByCreatedAtDesc();
	Optional<Post> findById(Long id);
}
