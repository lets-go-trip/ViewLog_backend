package com.trip.viewlog.review.controller.response;

import com.trip.viewlog.global.domain.File;
import com.trip.viewlog.review.domain.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class ReviewResponse {
    private Long id;
    private String username;
    private String content;
    private List<String> fileUrls;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .username(review.getUser().getName())
                .content(review.getContent())
                .fileUrls(review.getReviewFiles().stream().map(File::getFileName).collect(Collectors.toList()))
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }
}
