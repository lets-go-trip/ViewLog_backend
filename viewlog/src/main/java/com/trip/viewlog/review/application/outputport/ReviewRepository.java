package com.trip.viewlog.review.application.outputport;

import com.trip.viewlog.attraction.domain.Attraction;
import com.trip.viewlog.review.domain.Review;
import com.trip.viewlog.user.domain.User;

import java.util.List;

public interface ReviewRepository {
    Review findById(Long id);

    List<Review> findAllByUser(User user);

    List<Review> findAllByAttraction(Attraction attraction);

    Review save(Review review);

    void delete(Review review);
}
