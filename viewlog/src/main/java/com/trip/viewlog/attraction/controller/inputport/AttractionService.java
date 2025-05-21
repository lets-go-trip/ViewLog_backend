package com.trip.Attraction.controller.inputport;

import java.util.List;

import com.trip.Attraction.controller.response.AttractionResponse;

public interface AttractionService {
	List<AttractionResponse> findAttractionsNearby(double lat, double lng, Integer contentTypeId);

	List<AttractionResponse> findAttractionsByAddressKeyword(String keyword, double lat, double lng, Integer contentTypeId);
	
	List<AttractionResponse> findAttractionsByTitleKeyword(String keyword, double lat, double lng, Integer contentTypeId);
}
