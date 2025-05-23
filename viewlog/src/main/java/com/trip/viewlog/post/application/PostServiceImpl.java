package com.trip.viewlog.post.application;

import com.trip.viewlog.post.application.outputport.PostRepository;
import com.trip.viewlog.post.controller.inputport.PostService;
import com.trip.viewlog.post.controller.request.CreatePostRequest;
import com.trip.viewlog.post.controller.response.PostDetailResponse;
import com.trip.viewlog.post.controller.response.PostListResponse;
import com.trip.viewlog.post.domain.Post;
import com.trip.viewlog.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;

	@Override
	public Page<PostListResponse> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
		return postRepository.findAll(pageable).map(PostListResponse::from);
	}

	@Override
	public PostDetailResponse findById(Long id) {
		Post p = postRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Post not found with id=" + id));
		return PostDetailResponse.from(p);
	}

	@Override
	public PostDetailResponse createPost(CreatePostRequest req, User user) {
		// Build 도메인 Post에 user 전체 주입
		Post toSave = Post.builder().user(user).title(req.getTitle()).content(req.getContent())
				// author는 User.getName()으로 자동 설정
				.author(user.getName()).build();

		Post saved = postRepository.save(toSave);
		return PostDetailResponse.from(saved);
	}

	@Override
	public int remove(User user, Long postId) {
		return postRepository.deleteByUserAndpostId(user, postId);
	}
}
