package com.trip.viewlog.post.application;

import com.trip.viewlog.post.application.outputport.PostRepository;
import com.trip.viewlog.post.controller.inputport.PostService;
import com.trip.viewlog.post.domain.Post;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;
	
	@Override
	public List<Post> findAll() {
		return postRepository.findAllByOrderByCreatedAtDesc();
	}
	
	@Override
	public Post findById(Long id) {
		return postRepository.findById(id)
		        .orElseThrow(() -> new EntityNotFoundException("Post not found with id=" + id));
	}
}
