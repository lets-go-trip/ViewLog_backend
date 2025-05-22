package com.trip.viewlog.post.controller.inputport;

import com.trip.viewlog.post.controller.request.CreatePostRequest;
import com.trip.viewlog.post.controller.response.PostDetailResponse;
import com.trip.viewlog.post.controller.response.PostListResponse;
import com.trip.viewlog.post.domain.Post;
import com.trip.viewlog.user.domain.User;
import org.springframework.data.domain.Page;

public interface PostService {
	Page<PostListResponse> findAll(int page, int size);

	Post findById(Long id);

	PostDetailResponse createPost(CreatePostRequest req, User user);
}
