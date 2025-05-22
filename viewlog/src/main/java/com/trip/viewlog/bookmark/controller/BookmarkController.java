package com.trip.viewlog.bookmark.controller;

import com.trip.viewlog.bookmark.controller.inputport.BookmarkService;
import com.trip.viewlog.bookmark.domain.Bookmark;
import com.trip.viewlog.global.dto.CustomOAuth2User;
import com.trip.viewlog.user.controller.inputport.UserService;
import com.trip.viewlog.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;
    private final UserService userService;

    /**
     * 즐겨찾기 상태 조회
     * GET /api/bookmarks/{id}/exists
     */
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> exists(
            @AuthenticationPrincipal CustomOAuth2User principal,
            @PathVariable("id") Long attractionId
    ) {
        String oauthInfo = principal.getOauthInfo();
        User user = userService.getByOauthInfo(oauthInfo);
        boolean exists = bookmarkService.exists(user, attractionId);
        return ResponseEntity.ok(exists);
    }

    /**
     * 즐겨찾기 추가
     * POST /api/bookmarks/{id}
     */
    @PostMapping("/{id}")
    public ResponseEntity<Void> add(
            @AuthenticationPrincipal CustomOAuth2User principal,
            @PathVariable("id") Long attractionId
    ) {
        String oauthInfo = principal.getOauthInfo();
        User user = userService.getByOauthInfo(oauthInfo);
        bookmarkService.add(user, attractionId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 즐겨찾기 해제
     * DELETE /api/bookmarks/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(
            @AuthenticationPrincipal CustomOAuth2User principal,
            @PathVariable("id") Long attractionId
    ) {
        String oauthInfo = principal.getOauthInfo();
        User user = userService.getByOauthInfo(oauthInfo);
        bookmarkService.remove(user, attractionId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/ids")
    public ResponseEntity<List<Long>> getAllIdByUser(
            @AuthenticationPrincipal CustomOAuth2User principal
    ) {
        String oauthInfo = principal.getOauthInfo();
        User user = userService.getByOauthInfo(oauthInfo);
        List<Long> results = bookmarkService.getAllByUser(user)
        		.stream()
                .map(Bookmark::getAttractionId)
                .toList();
        return ResponseEntity.ok(results);
    }
}
