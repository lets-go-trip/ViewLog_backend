package com.trip.viewlog.post.application.outputport;

import com.trip.viewlog.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
	Page<Post> findAll(Pageable pageable);
	Optional<Post> findById(Long id);
	Post save(Post post);
}
