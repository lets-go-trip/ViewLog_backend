package com.trip.viewlog.global.entity;

import com.trip.viewlog.global.domain.File;
import com.trip.viewlog.global.entity.enumeration.FileType;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long targetId;

    private String fileName;

    private String originalName;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    public static FileEntity from(File file) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.id = file.getId();
        fileEntity.targetId = file.getTargetId();
        fileEntity.fileName = file.getFileName();
        fileEntity.originalName = file.getOriginalName();
        fileEntity.fileType = file.getFileType();
        return fileEntity;
    }

    public File toModel() {
        return File.builder()
                .id(id)
                .targetId(targetId)
                .fileName(fileName)
                .originalName(originalName)
                .fileType(fileType)
                .build();
    }

}
