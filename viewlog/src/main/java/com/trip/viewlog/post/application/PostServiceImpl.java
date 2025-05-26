package com.trip.viewlog.post.application;

import com.trip.viewlog.global.application.S3Service;
import com.trip.viewlog.global.domain.File;
import com.trip.viewlog.global.entity.FileEntity;
import com.trip.viewlog.global.entity.enumeration.FileType;
import com.trip.viewlog.global.infrastructure.FileJpaRepository;
import com.trip.viewlog.post.application.outputport.PostRepository;
import com.trip.viewlog.post.controller.inputport.PostService;
import com.trip.viewlog.post.controller.request.CreatePostRequest;
import com.trip.viewlog.post.controller.response.PostDetailResponse;
import com.trip.viewlog.post.controller.response.PostListResponse;
import com.trip.viewlog.post.domain.Post;
import com.trip.viewlog.user.application.outputport.UserRepository;
import com.trip.viewlog.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final S3Service s3Service;
    private final FileJpaRepository fileJpaRepository;
	
	@Override
	public Page<PostListResponse> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

	    // 1) Post 페이징 조회 (쿼리 #1)
	    Page<Post> postPage = postRepository.findAll(pageable);

	    // 2) Post ID만 꺼내기
	    List<Long> postIds = postPage.stream()
	        .map(Post::getId)
	        .toList();

	    if (postIds.isEmpty()) {
	        // 빈 페이지면 바로 빈 DTO 페이지 리턴
	        return postPage.map(PostListResponse::from);
	    }

	    // 3) 해당 포스트들에 속한 모든 파일 조회 (쿼리 #2)
	    List<FileEntity> files = fileJpaRepository
	        .findByFileTypeAndTargetIdIn(FileType.POST, postIds);

	    // 4) 포스트 ID별로 첫 번째 파일 URL만 뽑아서 맵으로 저장
	    Map<Long, String> thumbnailMap = files.stream()
	        .collect(Collectors.groupingBy(
	            FileEntity::getTargetId,
	            // 그룹당 맵핑된 List<FileEntity>에서 첫 번째 요소(toModel().getUrl())만 뽑기
	            Collectors.collectingAndThen(
	                Collectors.toList(),
	                lst -> lst.get(0).toModel().getFileUrl()
	            )
	        ));
	    
	    return postPage.map(post -> {
	        String thumb = thumbnailMap.getOrDefault(post.getId(), "");
	        return PostListResponse.builder()
	            .id(post.getId())
	            .title(post.getTitle())
	            .author(post.getAuthor())
	            .fileUrl(thumb)             // 단일 URL
	            .createdAt(post.getCreatedAt())
	            .build();
	    });
	}

	@Override
	public PostDetailResponse findById(Long id) {
		Post p = postRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Post not found with id=" + id));
		List<FileEntity> fileEntities = fileJpaRepository.findByFileTypeAndTargetId(FileType.POST, id);
        for (FileEntity fileEntity : fileEntities) {
            p.addFile(fileEntity.toModel());
        }
		return PostDetailResponse.from(p);
	}

	@Override
	@Transactional
	public PostDetailResponse createPost(CreatePostRequest req, Long userId, List<MultipartFile> images) {
		User u = userRepository.findById(userId)
    		    .orElseThrow(() -> new EntityNotFoundException("User not found: " + userId));
		
		Post toSave = Post.create(req, u);
		Post saved = postRepository.save(toSave);
		
		if (images != null) {
            List<String> fileUrls = s3Service.uploadFile(images);
            for (int i = 0; i < images.size(); i++) {
                File file = File.builder()
                        .fileUrl(fileUrls.get(i))
                        .originalName(images.get(i).getOriginalFilename())
                        .fileType(FileType.POST)
                        .targetId(saved.getId())
                        .build();
                saved.addFile(fileJpaRepository.save(FileEntity.from(file)).toModel());
            }
        }
		
		return PostDetailResponse.from(saved);
	}

	@Override
	public int remove(Long userId, Long postId) {
		Post post = postRepository.findById(postId).orElse(null);
		List<FileEntity> fileEntities = fileJpaRepository.findByFileTypeAndTargetId(FileType.POST, post.getId());
        for (FileEntity fileEntity : fileEntities) {
            s3Service.deleteFile(fileEntity.getFileUrl());
            fileJpaRepository.delete(fileEntity);
        }
		return postRepository.deleteByUsersIdAndpostId(userId, postId);
	}

	@Override
	@Transactional
	public int updatePost(Long userId, Long postId, CreatePostRequest dto, List<MultipartFile> images) {
		Post post = postRepository.findById(postId).orElse(null);
		if (post == null) return 0;

		// 작성자 확인
		if (!post.getUser().getId().equals(userId)) {
			return 0;
		}

		post = post.update(dto);
		List<FileEntity> fileEntities = fileJpaRepository.findByFileTypeAndTargetId(FileType.POST, postId);

        for (FileEntity fileEntity : fileEntities) {
            s3Service.deleteFile(fileEntity.getFileUrl());
            fileJpaRepository.delete(fileEntity);
        }

        if (images != null) {
            List<String> fileUrls = s3Service.uploadFile(images);
            for (int i = 0; i < images.size(); i++) {
                File file = File.builder()
                        .fileUrl(fileUrls.get(i))
                        .originalName(images.get(i).getOriginalFilename())
                        .fileType(FileType.POST)
                        .targetId(post.getId())
                        .build();
                post.addFile(fileJpaRepository.save(FileEntity.from(file)).toModel());
            }
        }
		
		postRepository.save(post);
		return 1;
	}
	
	
}
