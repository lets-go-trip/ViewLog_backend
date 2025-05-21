package com.trip.Attraction.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Attraction {
	private Integer id;
    private Integer contentId;
    private ContentType contentType;
    private Integer areaCode;
    private Integer siGunGuCode;
    private String title;
    private String firstImage1;
    private String firstImage2;
    private Integer mapLevel;
    private Double latitude;
    private Double longitude;
    private String tel;
    private String addr1;
    private String addr2;
    private String homepage;
    private String overview;
    
    @Builder
	public Attraction(Integer id, Integer contentId, ContentType contentType, Integer areaCode,
			Integer siGunGuCode, String title, String firstImage1, String firstImage2, Integer mapLevel,
			Double latitude, Double longitude, String tel, String addr1, String addr2, String homepage,
			String overview) {
		super();
		this.id = id;
		this.contentId = contentId;
		this.contentType = contentType;
		this.areaCode = areaCode;
		this.siGunGuCode = siGunGuCode;
		this.title = title;
		this.firstImage1 = firstImage1;
		this.firstImage2 = firstImage2;
		this.mapLevel = mapLevel;
		this.latitude = latitude;
		this.longitude = longitude;
		this.tel = tel;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.homepage = homepage;
		this.overview = overview;
	}
    
}