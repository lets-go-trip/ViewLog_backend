package com.trip.viewlog.attraction.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Attraction {
	private final Long id;
    private final Integer contentId;
    private final ContentType contentType;
    private final Integer areaCode;
    private final Integer siGunGuCode;
    private final String title;
    private final String firstImage1;
    private final String firstImage2;
    private final Integer mapLevel;
    private final Double latitude;
    private final Double longitude;
    private final String tel;
    private final String addr1;
    private final String addr2;
    private final String homepage;
    private final String overview;
    
    @Builder
	public Attraction(Long id, Integer contentId, ContentType contentType, Integer areaCode,
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