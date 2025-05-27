package com.trip.viewlog.attraction.controller;

import com.trip.viewlog.attraction.controller.inputport.AttractionService;
import com.trip.viewlog.attraction.controller.response.AttractionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/attractions")
@RequiredArgsConstructor
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping("/nearby")
    public ResponseEntity<List<AttractionResponse>> getNearbyAttractions(
        @RequestParam double lat,
        @RequestParam double lng,
        @RequestParam(required = false) Integer contentTypeId
    ) {
    	List<AttractionResponse> result = attractionService.findAttractionsNearby(lat, lng, contentTypeId);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/searchByAddress")
    public ResponseEntity<List<AttractionResponse>> searchByAddress(
        @RequestParam String keyword,
        @RequestParam double lat,
        @RequestParam double lng,
        @RequestParam(required = false) Integer contentTypeId
    ) {
    	List<AttractionResponse> result = attractionService.findAttractionsByAddressKeyword(keyword, lat, lng, contentTypeId);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/searchByName")
    public ResponseEntity<List<AttractionResponse>> searchByName(
        @RequestParam String keyword,
        @RequestParam double lat,
        @RequestParam double lng,
        @RequestParam(required = false) Integer contentTypeId
    ) {
    	List<AttractionResponse> result = attractionService.findAttractionsByTitleKeyword(keyword, lat, lng, contentTypeId);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/searchById")
    public ResponseEntity<AttractionResponse> searchById(@RequestParam Long id) {
    	AttractionResponse result = attractionService.findById(id);
        if (result==null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }
}
