package com.trip.viewlog.global.infrastructure;

import com.trip.viewlog.global.entity.FileEntity;
import com.trip.viewlog.global.entity.enumeration.FileType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileJpaRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByFileTypeAndTargetId(FileType fileType, Long targetId);
}
