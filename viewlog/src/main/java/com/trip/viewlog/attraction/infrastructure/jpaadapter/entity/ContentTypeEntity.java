package com.trip.viewlog.attraction.infrastructure.jpaadapter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "contenttypes")
@Getter
public class ContentTypeEntity {
    @Id
    @Column(name = "content_type_id", nullable = false)
    private Integer contentTypeId;

    @Column(name = "content_type_name", length = 45)
    private String contentTypeName;
    
}
