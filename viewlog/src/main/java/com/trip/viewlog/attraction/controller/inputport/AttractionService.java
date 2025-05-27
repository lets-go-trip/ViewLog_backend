package com.trip.viewlog.attraction.controller.inputport;

import com.trip.viewlog.attraction.controller.response.AttractionResponse;

import java.util.List;

public interface AttractionService {
	List<AttractionResponse> findAttractionsNearby(double lat, double lng, Integer contentTypeId);

	List<AttractionResponse> findAttractionsByAddressKeyword(String keyword, double lat, double lng, Integer contentTypeId);
	
	List<AttractionResponse> findAttractionsByTitleKeyword(String keyword, double lat, double lng, Integer contentTypeId);

	AttractionResponse findById(Long id);
}
