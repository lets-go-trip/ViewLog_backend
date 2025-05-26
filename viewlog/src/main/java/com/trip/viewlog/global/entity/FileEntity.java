package com.trip.viewlog.global.entity;

import com.trip.viewlog.global.domain.File;
import com.trip.viewlog.global.entity.enumeration.FileType;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "files")
@Getter
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long targetId;

    private String fileUrl;

    private String originalName;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    public static FileEntity from(File file) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.id = file.getId();
        fileEntity.targetId = file.getTargetId();
        fileEntity.fileUrl = file.getFileUrl();
        fileEntity.originalName = file.getOriginalName();
        fileEntity.fileType = file.getFileType();
        return fileEntity;
    }

    public File toModel() {
        return File.builder()
                .id(id)
                .targetId(targetId)
                .fileUrl(fileUrl)
                .originalName(originalName)
                .fileType(fileType)
                .build();
    }

}
