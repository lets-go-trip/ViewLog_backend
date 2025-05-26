package com.trip.viewlog.post.controller.inputport;

import com.trip.viewlog.post.controller.request.CreatePostRequest;
import com.trip.viewlog.post.controller.response.PostDetailResponse;
import com.trip.viewlog.post.controller.response.PostListResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
	Page<PostListResponse> findAll(int page, int size);

	PostDetailResponse findById(Long id);

	PostDetailResponse createPost(CreatePostRequest req, Long userId, List<MultipartFile> files);

	int remove(Long userId, Long postId);

	int updatePost(Long userId, Long postId, CreatePostRequest dto, List<MultipartFile> files);
}
