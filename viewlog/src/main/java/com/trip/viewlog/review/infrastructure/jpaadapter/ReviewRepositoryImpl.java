package com.trip.viewlog.review.infrastructure.jpaadapter;

import com.trip.viewlog.attraction.domain.Attraction;
import com.trip.viewlog.attraction.infrastructure.jpaadapter.entity.AttractionEntity;
import com.trip.viewlog.review.application.outputport.ReviewRepository;
import com.trip.viewlog.review.domain.Review;
import com.trip.viewlog.review.infrastructure.jpaadapter.entity.ReviewEntity;
import com.trip.viewlog.user.domain.User;
import com.trip.viewlog.user.infrastructure.jpaadapter.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {
    private final ReviewJpaRepository reviewJpaRepository;

    @Override
    public Review findById(Long id) {
        ReviewEntity reviewEntity = reviewJpaRepository.findById(id).orElse(null);
        return reviewEntity.toModel();
    }

    @Override
    public List<Review> findAllByUser(User user) {
        List<ReviewEntity> reviewEntities = reviewJpaRepository.findAllByUserEntity(UserEntity.from(user));
        return getReviews(reviewEntities);
    }

    @Override
    public List<Review> findAllByAttraction(Attraction attraction) {
        List<ReviewEntity> reviewEntities = reviewJpaRepository.findAllByAttractionEntity(AttractionEntity.from(attraction));
        return getReviews(reviewEntities);
    }

    @Override
    public Review save(Review review) {
        return reviewJpaRepository.save(ReviewEntity.from(review)).toModel();
    }

    @Override
    public void delete(Review review) {
        reviewJpaRepository.delete(ReviewEntity.from(review));
    }

    private List<Review> getReviews(List<ReviewEntity> reviewEntities) {
        List<Review> reviews = new ArrayList<>();
        for (ReviewEntity reviewEntity : reviewEntities) {
            reviews.add(reviewEntity.toModel());
        }
        return reviews;
    }
}
