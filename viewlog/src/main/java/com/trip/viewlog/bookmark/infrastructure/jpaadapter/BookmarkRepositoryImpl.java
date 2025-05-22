package com.trip.viewlog.bookmark.infrastructure.jpaadapter;

import com.trip.viewlog.bookmark.application.outputport.BookmarkRepository;
import com.trip.viewlog.bookmark.domain.Bookmark;
import com.trip.viewlog.bookmark.infrastructure.jpaadapter.entity.BookmarkEntity;
import com.trip.viewlog.user.domain.User;
import com.trip.viewlog.user.infrastructure.jpaadapter.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements BookmarkRepository {
    private final BookmarkJpaRepository jpa;

    @Override
    public Optional<Bookmark> findByUserIdAndAttractionId(User user, Long attractionId) {
        // UserEntity proxy 로 연결
    	UserEntity userEntity = UserEntity.from(user);
        return jpa.findByUserEntityAndAttractionId(userEntity, attractionId)
                  .map(BookmarkEntity::toModel);
    }

    @Override
    public void save(Bookmark bookmark) {
        BookmarkEntity entity = BookmarkEntity.from(bookmark);
        jpa.save(entity);
    }

    @Override
    public void deleteByUserIdAndAttractionId(User user, Long attractionId) {
    	UserEntity userEntity = UserEntity.from(user);
        jpa.deleteByUserEntityAndAttractionId(userEntity, attractionId);
    }

	@Override
	public List<Bookmark> findByUser(User user) {
		UserEntity userEntity = UserEntity.from(user);
		return jpa.findByUserEntity(userEntity)
				.stream()
				.map(BookmarkEntity::toModel)
				.toList();
	}
    
}