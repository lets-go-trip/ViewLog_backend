package com.trip.viewlog.review.infrastructure.jpaadapter;

import com.trip.viewlog.attraction.infrastructure.jpaadapter.entity.AttractionEntity;
import com.trip.viewlog.review.infrastructure.jpaadapter.entity.ReviewEntity;
import com.trip.viewlog.user.infrastructure.jpaadapter.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewJpaRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAllByUserEntity(UserEntity userEntity);

    List<ReviewEntity> findAllByAttractionEntity(AttractionEntity attractionEntity);
}
