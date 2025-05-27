package com.trip.viewlog.bookmark.infrastructure.jpaadapter.entity;

import com.trip.viewlog.attraction.infrastructure.jpaadapter.entity.AttractionEntity;
import com.trip.viewlog.bookmark.domain.Bookmark;
import com.trip.viewlog.user.infrastructure.jpaadapter.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookmark", uniqueConstraints = @UniqueConstraint(name = "uq_user_attraction", columnNames = { "users_id",
		"attraction_id" }))
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_id", nullable = false, foreignKey = @ForeignKey(name = "fk_bm_user"))
	private UserEntity userEntity;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "attraction_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_bm_attraction")
    )
    private AttractionEntity attractionEntity;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	public static BookmarkEntity from(Bookmark bookmark) {
		BookmarkEntity entity = new BookmarkEntity();
		entity.id = bookmark.getId(); // optional
		entity.userEntity = UserEntity.from(bookmark.getUser());
		entity.attractionEntity = AttractionEntity.from(bookmark.getAttraction());
		entity.createdAt = bookmark.getCreatedAt();
		return entity;
	}

	public Bookmark toModel() {
		return Bookmark.builder().id(id).user(userEntity.toModel()).attraction(attractionEntity.toModel())
				.createdAt(createdAt).build();
	}
}