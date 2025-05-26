package com.trip.viewlog.post.infrastructure.jpaadapter.entity;

import com.trip.viewlog.post.domain.Post;
import com.trip.viewlog.user.infrastructure.jpaadapter.entity.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Table(name = "post")
public class PostEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_id", nullable = false,
			foreignKey = @ForeignKey(name = "fk_post_users"))
	private UserEntity userEntity;

	@Column(nullable = false, length = 255)
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	@Column(nullable = false, length = 100)
	private String author;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	public static PostEntity from(Post post) {
		PostEntity entity = new PostEntity();
		entity.id = post.getId();
		entity.userEntity = UserEntity.from(post.getUser());
		entity.title = post.getTitle();
		entity.content = post.getContent();
		entity.author = post.getAuthor();
		entity.createdAt = post.getCreatedAt();
		entity.updatedAt = post.getUpdatedAt();
		return entity;
	}

	public Post toModel() {
		return Post.builder().id(id).user(userEntity.toModel())
				.title(title)
				.content(content)
				.author(author)
				.postFiles(new ArrayList<>())
				.createdAt(createdAt)
				.updatedAt(updatedAt).build();
	}
}
