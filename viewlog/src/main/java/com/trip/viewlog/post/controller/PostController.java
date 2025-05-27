package com.trip.viewlog.post.controller;

import com.trip.viewlog.global.dto.CustomOAuth2User;
import com.trip.viewlog.post.controller.inputport.PostService;
import com.trip.viewlog.post.controller.request.CreatePostRequest;
import com.trip.viewlog.post.controller.response.PostDetailResponse;
import com.trip.viewlog.post.controller.response.PostListResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	@GetMapping
	public ResponseEntity<Page<PostListResponse>> findAllPosts(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "9") int size
	) {
		Page<PostListResponse> result = postService.findAll(page,size);
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PostDetailResponse> getPostById(@PathVariable("id") Long id) {
		try {
			PostDetailResponse post = postService.findById(id);
			return ResponseEntity.ok(post);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/create")
	public ResponseEntity<PostDetailResponse> createPost(
			@AuthenticationPrincipal CustomOAuth2User principal,         // JWT 전체를 주입
			@ModelAttribute CreatePostRequest req,
			@RequestPart(required = false) List<MultipartFile> files
	) {
		// 3) 서비스 호출
		PostDetailResponse dto = postService.createPost(req, principal.getUserId(), files);

		// 4) 201 응답
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(
			@AuthenticationPrincipal CustomOAuth2User principal,
			@PathVariable("id") Long PostId
	) {
		int deleted = postService.remove(principal.getUserId(), PostId);

		if (deleted == 1) {
			// 204 No Content: 정상 삭제
			return ResponseEntity.noContent().build();
		} else {
			// 403 Forbidden: 권한 없거나 게시글 없음
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updatePost(
			@AuthenticationPrincipal CustomOAuth2User principal,
			@PathVariable("id") Long postId,
			@ModelAttribute CreatePostRequest dto,
			@RequestPart(required = false) List<MultipartFile> files
	) {
		int updated = postService.updatePost(principal.getUserId(), postId, dto, files);

		if (updated == 1) {
			// 204 No Content: 정상 삭제
			return ResponseEntity.noContent().build();
		} else {
			// 403 Forbidden: 권한 없거나 게시글 없음
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	
	@GetMapping("/my")
	public ResponseEntity<List<PostListResponse>> getPostByMyId(
			@AuthenticationPrincipal CustomOAuth2User principal) {
		try {
			List<PostListResponse> posts = postService.findByUserId(principal.getUserId());
			return ResponseEntity.ok(posts);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
