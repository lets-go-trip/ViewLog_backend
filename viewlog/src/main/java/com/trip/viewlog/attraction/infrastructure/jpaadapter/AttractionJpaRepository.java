package com.trip.viewlog.attraction.infrastructure.jpaadapter;

import com.trip.viewlog.attraction.infrastructure.jpaadapter.entity.AttractionEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttractionJpaRepository extends JpaRepository<AttractionEntity, Long> {

	@EntityGraph(attributePaths = "contentTypeEntity")
	List<AttractionEntity> findByLatitudeBetweenAndLongitudeBetween(double minLat, double maxLat, double minLng, double maxLng);
	
	@EntityGraph(attributePaths = "contentTypeEntity")
	List<AttractionEntity> findByAddr1ContainingOrAddr2Containing(String addr1Keyword, String addr2Keyword);
	
	@EntityGraph(attributePaths = "contentTypeEntity")
	List<AttractionEntity> findByTitleContaining(String keyword);
}
