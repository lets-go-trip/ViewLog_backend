package com.trip.viewlog.global.entity.enumeration;

public enum FileType {
    POST("게시글 이미지"),
    REVIEW("리뷰 이미지"),
    PROFILE("프로필 이미지");

    private final String description;

    FileType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
