package com.trip.viewlog.review.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewRequest {

    @Schema(description = "관광지 ID", example = "1")
    private final Long attractionId;

    @Schema(description = "리뷰 내용", example = "너무 좋아요!")
    private final String content;

    @Builder
    public ReviewRequest(
            @JsonProperty("attractionId") Long attractionId,
            @JsonProperty("content") String content) {
        this.attractionId = attractionId;
        this.content = content;
    }
}
