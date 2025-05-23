package com.trip.viewlog.review.controller.inputport;

import com.trip.viewlog.review.controller.request.ReviewRequest;
import com.trip.viewlog.review.domain.Review;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {
    Review getById(Long id);

    List<Review> getAllByUser(String oauthInfo);

    List<Review> getAllByAttraction(Long attractionId);

    Review create(String oauthInfo, ReviewRequest reviewRequest, List<MultipartFile> files);

    Review update(Long reviewId, ReviewRequest reviewRequest, List<MultipartFile> files);

    void delete(Long reviewId);
}
