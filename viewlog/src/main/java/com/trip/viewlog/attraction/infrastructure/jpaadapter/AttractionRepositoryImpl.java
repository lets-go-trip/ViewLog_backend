package com.trip.viewlog.attraction.infrastructure.jpaadapter;

import com.trip.viewlog.attraction.application.outputport.AttractionRepository;
import com.trip.viewlog.attraction.domain.Attraction;
import com.trip.viewlog.attraction.infrastructure.jpaadapter.entity.AttractionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AttractionRepositoryImpl implements AttractionRepository {
	private final AttractionJpaRepository attractionJpaRepository;

	@Override
    public List<Attraction> findByLatitudeBetweenAndLongitudeBetween(double minLat, double maxLat, double minLng, double maxLng) {
        return attractionJpaRepository
                .findByLatitudeBetweenAndLongitudeBetween(minLat, maxLat, minLng, maxLng)
                .stream()
                .map(AttractionEntity::toModel)      // Entity → Domain
                .collect(Collectors.toList());
    }

    @Override
    public List<Attraction> findByAddr1ContainingOrAddr2Containing(String addr1Keyword, String addr2Keyword) {
        return attractionJpaRepository
                .findByAddr1ContainingOrAddr2Containing(addr1Keyword, addr2Keyword)
                .stream()
                .map(AttractionEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Attraction> findByTitleContaining(String keyword) {
        return attractionJpaRepository
                .findByTitleContaining(keyword)
                .stream()
                .map(AttractionEntity::toModel)
                .collect(Collectors.toList());
    }
	
	
}
