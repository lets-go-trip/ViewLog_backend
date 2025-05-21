package com.trip.viewlog.post.controller;

import com.trip.viewlog.post.controller.inputport.PostService;
import com.trip.viewlog.post.domain.Post;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping("/all")
	public ResponseEntity<List<Post>> findAllPosts() {
		List<Post> result = postService.findAll();
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
		try {
	        Post post = postService.findById(id);
	        return ResponseEntity.ok(post);
	    } catch (EntityNotFoundException e) {
	        return ResponseEntity.notFound().build();
	    }
	}

}
