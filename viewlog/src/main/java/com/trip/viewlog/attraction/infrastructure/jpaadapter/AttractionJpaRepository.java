package com.trip.Attraction.infrastructure.jpaadapter;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.trip.Attraction.infrastructure.jpaadapter.entity.AttractionEntity;

public interface AttractionJpaRepository extends JpaRepository<AttractionEntity, Integer> {

	@EntityGraph(attributePaths = "contentTypeEntity")
	List<AttractionEntity> findByLatitudeBetweenAndLongitudeBetween(double minLat, double maxLat, double minLng, double maxLng);
	
	@EntityGraph(attributePaths = "contentTypeEntity")
	List<AttractionEntity> findByAddr1ContainingOrAddr2Containing(String addr1Keyword, String addr2Keyword);
	
	@EntityGraph(attributePaths = "contentTypeEntity")
	List<AttractionEntity> findByTitleContaining(String keyword);
}
