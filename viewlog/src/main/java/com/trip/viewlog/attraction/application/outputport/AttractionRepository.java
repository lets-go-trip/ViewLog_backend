package com.trip.Attraction.application.outputport;

import java.util.List;

import com.trip.Attraction.domain.Attraction;

public interface AttractionRepository {
	List<Attraction> findByLatitudeBetweenAndLongitudeBetween(double minLat, double maxLat, double minLng, double maxLng);

	List<Attraction> findByAddr1ContainingOrAddr2Containing(String addr1Keyword, String addr2Keyword);

	List<Attraction> findByTitleContaining(String keyword);
}
