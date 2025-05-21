package com.trip.viewlog.attraction.infrastructure.jpaadapter.entity;

import com.trip.viewlog.attraction.domain.ContentType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contenttypes")
@NoArgsConstructor
@AllArgsConstructor
public class ContentTypeEntity {
    @Id
    @Column(name = "content_type_id", nullable = false)
    private Integer id;

    @Column(name = "content_type_name", length = 45)
    private String name;
    
    public static ContentTypeEntity from(ContentType contentType) {
    	ContentTypeEntity entity = new ContentTypeEntity();
        entity.id = contentType.getId();
        entity.name = contentType.getName();
        return entity;
    }
    
    public ContentType toModel() {
        return ContentType.builder()
            .id(id)
            .name(name)
            .build();
    }
    
}
