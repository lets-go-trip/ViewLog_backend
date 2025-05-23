package com.trip.viewlog.post.infrastructure.jpaadapter;

import com.trip.viewlog.post.application.outputport.PostRepository;
import com.trip.viewlog.post.domain.Post;
import com.trip.viewlog.post.infrastructure.jpaadapter.entity.PostEntity;
import com.trip.viewlog.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

	private final PostJpaRepository postJpaRepository;

	@Override
	public Page<Post> findAll(Pageable pageable) {
		return postJpaRepository.findAll(pageable)
				.map(PostEntity::toModel);
	}

	@Override
	public Optional<Post> findById(Long id) {
		return postJpaRepository.findById(id).map(PostEntity::toModel);
	}

	@Override
	@Modifying
	@Transactional
	public Post save(Post post) {
		// 도메인 → 엔티티
		PostEntity entity = PostEntity.from(post);
		// JPA 저장
		PostEntity saved = postJpaRepository.save(entity);
		// 엔티티 → 도메인
		return saved.toModel();
	}

	@Override
	@Modifying
	@Transactional
	public int deleteByUserAndpostId(User user, Long postId) {
		return postJpaRepository.deleteByUserEntity_IdAndId(user.getId(), postId);
	}

}
