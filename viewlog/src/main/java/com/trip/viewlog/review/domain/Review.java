package com.trip.viewlog.review.domain;

import com.trip.viewlog.attraction.domain.Attraction;
import com.trip.viewlog.global.domain.File;
import com.trip.viewlog.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Review {
    private final Long id;
    private final User user;
    private final Attraction attraction;
    private final String content;
    private final List<File> reviewFiles;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public Review(Long id, User user, Attraction attraction, String content, List<File> reviewFiles, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.user = user;
        this.attraction = attraction;
        this.content = content;
        this.reviewFiles = reviewFiles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Review from(User user, Attraction attraction, String content) {
        return Review.builder()
                .user(user)
                .attraction(attraction)
                .content(content)
                .reviewFiles(new ArrayList<>())
                .build();
    }

    public Review update(String content) {
        return Review.builder()
                .id(id)
                .user(user)
                .attraction(attraction)
                .content(content)
                .reviewFiles(new ArrayList<>())
                .build();
    }

    public void addFile(File file) {
        reviewFiles.add(file);
    }

}
