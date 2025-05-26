package com.trip.viewlog.review.infrastructure.jpaadapter.entity;

import java.util.ArrayList;

import com.trip.viewlog.attraction.infrastructure.jpaadapter.entity.AttractionEntity;
import com.trip.viewlog.global.entity.BaseTimeEntity;
import com.trip.viewlog.review.domain.Review;
import com.trip.viewlog.user.infrastructure.jpaadapter.entity.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Table(name = "reviews")
@Getter
public class ReviewEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

    @JoinColumn(name = "attraction_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AttractionEntity attractionEntity;

    private String content;

    public static ReviewEntity from(Review review) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.id = review.getId();
        reviewEntity.userEntity = UserEntity.from(review.getUser());
        reviewEntity.attractionEntity = AttractionEntity.from(review.getAttraction());
        reviewEntity.content = review.getContent();
        return reviewEntity;
    }

    public Review toModel() {
        return Review.builder()
                .id(id)
                .user(userEntity.toModel())
                .attraction(attractionEntity.toModel())
                .content(content)
                .reviewFiles(new ArrayList<>())
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }
}
