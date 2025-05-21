package com.trip.viewlog.post.controller.inputport;

import com.trip.viewlog.post.domain.Post;

import java.util.List;

public interface PostService {
	List<Post> findAll();

	Post findById(Long id);
}
