package com.trip.viewlog.post.controller;

import com.trip.viewlog.global.dto.CustomOAuth2User;
import com.trip.viewlog.post.controller.inputport.PostService;
import com.trip.viewlog.post.controller.request.CreatePostRequest;
import com.trip.viewlog.post.controller.response.PostDetailResponse;
import com.trip.viewlog.post.controller.response.PostListResponse;
import com.trip.viewlog.post.domain.Post;
import com.trip.viewlog.user.controller.inputport.UserService;
import com.trip.viewlog.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	private final UserService userService;

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
			@RequestBody CreatePostRequest req
	) {
		// 1) 토큰에서 oauthInfo 클레임 꺼내기
		String oauthInfo = principal.getOauthInfo();

		// 2) DB에서 User 조회
		User user = userService.getByOauthInfo(oauthInfo);

		// 3) 서비스 호출
		PostDetailResponse dto = postService.createPost(req, user);

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
		String oauthInfo = principal.getOauthInfo();
		User user = userService.getByOauthInfo(oauthInfo);
		int deleted = postService.remove(user, PostId);

		if (deleted == 1) {
			// 204 No Content: 정상 삭제
			return ResponseEntity.noContent().build();
		} else {
			// 403 Forbidden: 권한 없거나 게시글 없음
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

}
