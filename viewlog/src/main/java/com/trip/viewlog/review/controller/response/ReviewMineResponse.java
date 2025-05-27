package com.trip.viewlog.review.controller.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.trip.viewlog.global.domain.File;
import com.trip.viewlog.review.domain.Review;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewMineResponse {
    private Long id;
    private String username;
    private String attractionName;
    private String content;
    private List<String> fileUrls;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ReviewMineResponse from(Review review) {
        return ReviewMineResponse.builder()
                .id(review.getId())
                .username(review.getUser().getName())
                .attractionName(review.getAttraction().getTitle())
                .content(review.getContent())
                .fileUrls(review.getReviewFiles().stream().map(File::getFileUrl).collect(Collectors.toList()))
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }
}