package com.trip.viewlog.attraction.infrastructure.jpaadapter.entity;

import com.trip.viewlog.attraction.domain.Attraction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "attractions")
@NoArgsConstructor
@AllArgsConstructor
public class AttractionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private Integer id;

    @Column(name = "content_id")
    private Integer contentId;
    
    @Column(length = 500)
    private String title;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_type_id", referencedColumnName = "content_type_id")
    private ContentTypeEntity contentTypeEntity;

    @Column(name = "area_code")
    private Integer areaCode;

    @Column(name = "si_gun_gu_code")
    private Integer siGunGuCode;

    @Column(name = "first_image1", length = 100)
    private String firstImage1;

    @Column(name = "first_image2", length = 100)
    private String firstImage2;

    @Column(name = "map_level")
    private Integer mapLevel;

    private Double latitude;

    private Double longitude;

    @Column(length = 20)
    private String tel;

    @Column(length = 100)
    private String addr1;

    @Column(length = 100)
    private String addr2;

    @Column(length = 100)
    private String homepage;

    @Column(length = 10000)
    private String overview;
    
    public static AttractionEntity from(Attraction attraction) {
    	AttractionEntity entity = new AttractionEntity();
        entity.id = attraction.getId(); // optional
        entity.contentId = attraction.getContentId();
        entity.contentTypeEntity = ContentTypeEntity.from(attraction.getContentType());
        entity.areaCode = attraction.getAreaCode();
        entity.siGunGuCode = attraction.getSiGunGuCode();
        entity.title = attraction.getTitle();
        entity.firstImage1 = attraction.getFirstImage1();
        entity.firstImage2 = attraction.getFirstImage2();
        entity.mapLevel = attraction.getMapLevel();
        entity.latitude = attraction.getLatitude();
        entity.longitude = attraction.getLongitude();
        entity.tel = attraction.getTel();
        entity.addr1 = attraction.getAddr1();
        entity.addr2 = attraction.getAddr2();
        entity.homepage = attraction.getHomepage();
        entity.overview = attraction.getOverview();

        return entity;
    }
    
    public Attraction toModel() {
        return Attraction.builder()
            .id(id)
            .contentId(contentId)
            .contentType(contentTypeEntity.toModel())
            .areaCode(areaCode)
            .siGunGuCode(siGunGuCode)
            .title(title)
            .firstImage1(firstImage1)
            .firstImage2(firstImage2)
            .mapLevel(mapLevel)
            .latitude(latitude)
            .longitude(longitude)
            .tel(tel)
            .addr1(addr1)
            .addr2(addr2)
            .homepage(homepage)
            .overview(overview)
            .build();
    }
}