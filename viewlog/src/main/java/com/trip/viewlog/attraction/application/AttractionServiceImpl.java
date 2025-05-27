package com.trip.viewlog.attraction.application;

import com.trip.viewlog.attraction.application.outputport.AttractionRepository;
import com.trip.viewlog.attraction.controller.inputport.AttractionService;
import com.trip.viewlog.attraction.controller.response.AttractionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {

	private final double RADIUS_KM = 5.0;
	private final AttractionRepository attractionRepository;

	public List<AttractionResponse> findAttractionsNearby(double lat, double lng, Integer contentTypeId) {
		// 1° 위도 ≈ 111km
		double latDelta = RADIUS_KM / 111.0;
		// 경도 1° 거리는 위도에 따라 다르므로 cos(lat) 적용
		double lngDelta = RADIUS_KM / (111.0 * Math.cos(Math.toRadians(lat)));

		double minLat = lat - latDelta;
		double maxLat = lat + latDelta;
		double minLng = lng - lngDelta;
		double maxLng = lng + lngDelta;

		return attractionRepository.findByLatitudeBetweenAndLongitudeBetween(minLat, maxLat, minLng, maxLng).stream()
				// contentTypeId 필터링 (전체일 땐 skip)
				.filter(e -> contentTypeId == null || e.getContentType().getId().equals(contentTypeId)).map(a -> {
					AttractionResponse res = AttractionResponse.from(a);
					double dist = calculateDistance(lat, lng, a.getLatitude(), a.getLongitude());
					res.setDistance(dist);
					return res;
				}).filter(e -> e.getDistance() <= RADIUS_KM)
				.sorted(Comparator.comparing(AttractionResponse::getDistance)).limit(50).collect(Collectors.toList());
	}

	public List<AttractionResponse> findAttractionsByAddressKeyword(String keyword, double lat, double lng,
			Integer contentTypeId) {
		return attractionRepository.findByAddr1ContainingOrAddr2Containing(keyword, keyword).stream()
				.filter(e -> contentTypeId == null || e.getContentType().getId().equals(contentTypeId)).map(a -> {
					AttractionResponse res = AttractionResponse.from(a);
					double dist = calculateDistance(lat, lng, a.getLatitude(), a.getLongitude());
					res.setDistance(dist);
					return res;
				}).sorted(Comparator.comparing(AttractionResponse::getDistance)).limit(100)
				.collect(Collectors.toList());
	}

	@Override
	public List<AttractionResponse> findAttractionsByTitleKeyword(String keyword, double lat, double lng,
			Integer contentTypeId) {
		return attractionRepository.findByTitleContaining(keyword).stream()
				.filter(e -> contentTypeId == null || e.getContentType().getId().equals(contentTypeId)).map(a -> {
					AttractionResponse res = AttractionResponse.from(a);
					double dist = calculateDistance(lat, lng, a.getLatitude(), a.getLongitude());
					res.setDistance(dist);
					return res;
				}).filter(e -> e.getDistance() <= RADIUS_KM)
				.sorted(Comparator.comparing(AttractionResponse::getDistance)).limit(50).collect(Collectors.toList());
	}

	public static double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
		double r = 6371; // 지구 반지름 (km)
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return r * c;
	}

	@Override
	public AttractionResponse findById(Long id) {
		return AttractionResponse.from(attractionRepository.findById(id).orElse(null));
	}
}