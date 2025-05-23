package com.trip.viewlog.attraction.application.outputport;

import com.trip.viewlog.attraction.domain.Attraction;

import java.util.List;
import java.util.Optional;

public interface AttractionRepository {
	List<Attraction> findByLatitudeBetweenAndLongitudeBetween(double minLat, double maxLat, double minLng, double maxLng);

	List<Attraction> findByAddr1ContainingOrAddr2Containing(String addr1Keyword, String addr2Keyword);

	List<Attraction> findByTitleContaining(String keyword);

	Optional<Attraction> findById(Long id);
}
