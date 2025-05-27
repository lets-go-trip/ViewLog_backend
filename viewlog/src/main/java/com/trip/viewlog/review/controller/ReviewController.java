package com.trip.viewlog.review.controller;

import com.trip.viewlog.global.dto.CustomOAuth2User;
import com.trip.viewlog.review.controller.inputport.ReviewService;
import com.trip.viewlog.review.controller.inputport.ReviewSummaryService;
import com.trip.viewlog.review.controller.request.ReviewRequest;
import com.trip.viewlog.review.controller.response.ReviewResponse;
import com.trip.viewlog.review.controller.response.ReviewSummaryResponse;
import com.trip.viewlog.review.domain.Review;
import com.trip.viewlog.review.domain.ReviewSummary;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewSummaryService reviewSummaryService;

    @Operation(summary = "장소 리뷰 리스트 조회 API")
    @GetMapping("")
    public ResponseEntity<List<ReviewResponse>> getAllByAttraction(@RequestParam Long attractionId) {
        List<Review> reviews = reviewService.getAllByAttraction(attractionId);
        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(ReviewResponse::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(reviewResponses, HttpStatus.OK);
    }

    @Operation(summary = "사용자 리뷰 리스트 조회 API")
    @GetMapping("/my")
    public ResponseEntity<List<ReviewMineResponse>> getMyReviews(@AuthenticationPrincipal CustomOAuth2User oAuth2User) {
        List<Review> reviews = reviewService.getAllByUser(oAuth2User.getOauthInfo());
        List<ReviewMineResponse> reviewResponses = reviews.stream()
                .map(ReviewMineResponse::from)
                .collect(Collectors.toList());
        return new ResponseEntity<>(reviewResponses, HttpStatus.OK);
    }

    @Operation(summary = "리뷰 작성 API")
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReviewResponse> create(@AuthenticationPrincipal CustomOAuth2User oAuth2User, @RequestPart ReviewRequest reviewRequest, @RequestPart(required = false) List<MultipartFile> files) {
        if (oAuth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Review review = reviewService.create(oAuth2User.getOauthInfo(), reviewRequest, files);
        return ResponseEntity.ok(ReviewResponse.from(review));
    }

    @Operation(summary = "리뷰 단건 조회 API")
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> getById(@PathVariable Long reviewId) {
        Review review = reviewService.getById(reviewId);
        return new ResponseEntity<>(ReviewResponse.from(review), HttpStatus.OK);
    }

    @Operation(summary = "리뷰 수정 API")
    @PutMapping(value = "/{reviewId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReviewResponse> update(@PathVariable Long reviewId, @AuthenticationPrincipal CustomOAuth2User oAuth2User, @RequestPart ReviewRequest reviewRequest, @RequestPart(required = false) List<MultipartFile> files) {
        if (oAuth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Review review = reviewService.update(reviewId, reviewRequest, files);
        return ResponseEntity.ok(ReviewResponse.from(review));
    }

    @Operation(summary = "리뷰 삭제 API")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Objects> delete(@PathVariable Long reviewId, @AuthenticationPrincipal CustomOAuth2User oAuth2User) {
        if (oAuth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        reviewService.delete(reviewId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "리뷰 요약 API")
    @GetMapping("/summary/{attractionId}")
    public ResponseEntity<ReviewSummaryResponse> getSummary(@PathVariable Long attractionId) {
        ReviewSummary reviewSummary = reviewSummaryService.getOrCreateOrUpdate(attractionId);
        return new ResponseEntity<>(ReviewSummaryResponse.from(reviewSummary), HttpStatus.OK);
    }
}
