package com.trip.viewlog.global.domain;

import com.trip.viewlog.global.entity.enumeration.FileType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class File {
    private final Long id;
    private final Long targetId;
    private final String fileUrl;
    private final String originalName;
    private final FileType fileType;

    @Builder
    public File(Long id, Long targetId, String fileUrl, String originalName, FileType fileType) {
        this.id = id;
        this.targetId = targetId;
        this.fileUrl = fileUrl;
        this.originalName = originalName;
        this.fileType = fileType;
    }
}
