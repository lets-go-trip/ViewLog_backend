package com.trip.viewlog.attraction.controller.response;

import com.trip.viewlog.attraction.domain.Attraction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AttractionResponse {
	private Integer no;
	private Integer contentTypeId;
    private String title;
    private Double latitude;
    private Double longitude;
    private String firstImage1;
    private String tel;
    private String addr1;
    private String addr2;
    private String homepage;
    private String overview;
    private double distance; // 반경 거리 계산 값
    private String category;
    
    public static AttractionResponse from(Attraction attraction) {
    	return AttractionResponse.builder()
    			.no(attraction.getNo())
                .contentTypeId(attraction.getContentTypeEntity().getContentTypeId())
                .title(attraction.getTitle())
                .latitude(attraction.getLatitude())
                .longitude(attraction.getLongitude())
                .firstImage1(attraction.getFirstImage1())
                .tel(attraction.getTel())
                .addr1(attraction.getAddr1())
                .addr2(attraction.getAddr2())
                .homepage(attraction.getHomepage())
                .overview(attraction.getOverview())
                .category(attraction.getContentTypeEntity().getContentTypeName())
                .build();
    }
}
