package com.trip.viewlog.review.application;

import com.trip.viewlog.attraction.application.outputport.AttractionRepository;
import com.trip.viewlog.attraction.domain.Attraction;
import com.trip.viewlog.review.application.outputport.ReviewGptService;
import com.trip.viewlog.review.application.outputport.ReviewRepository;
import com.trip.viewlog.review.application.outputport.ReviewSummaryRepository;
import com.trip.viewlog.review.controller.inputport.ReviewSummaryService;
import com.trip.viewlog.review.domain.Review;
import com.trip.viewlog.review.domain.ReviewSummary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewSummaryServiceImpl implements ReviewSummaryService {
    private final ReviewSummaryRepository reviewSummaryRepository;
    private final ReviewGptService reviewGptService;
    private final ReviewRepository reviewRepository;
    private final AttractionRepository attractionRepository;

    public ReviewSummary getOrCreateOrUpdate(Long attractionId) {
        Attraction attraction = attractionRepository.findById(attractionId).orElse(null);
        ReviewSummary reviewSummary = reviewSummaryRepository.findByAttraction(attraction).orElse(null);
        if (reviewSummary == null || isStale(reviewSummary.getUpdatedAt())) {
            List<Review> reviews = reviewRepository.findAllByAttraction(attraction);
            if (reviews.isEmpty()) {
                throw new IllegalStateException("리뷰가 없어 요약할 수 없습니다.");
            }
            List<String> reviewContents = reviews.stream().map(Review::getContent).toList();

            String summary = reviewGptService.getSummary(attraction.getContentType().getName(), reviewContents);

            if (reviewSummary == null) {
                reviewSummary = ReviewSummary.from(attraction, summary);
                return reviewSummaryRepository.save(reviewSummary);
            }else {
                reviewSummary = reviewSummary.update(attraction, summary);
                return reviewSummaryRepository.save(reviewSummary);
            }
        }
        return reviewSummary;
    }

    private boolean isStale(LocalDateTime updatedAt) {
        return updatedAt.isBefore(LocalDateTime.now().minusDays(1));
    }
}
