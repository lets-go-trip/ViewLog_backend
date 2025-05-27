package com.trip.viewlog.bookmark.controller.inputport;

import com.trip.viewlog.bookmark.domain.Bookmark;

import java.util.List;

public interface BookmarkService {
    /**
     * 특정 사용자가 특정 명소를 즐겨찾기했는지 여부 확인
     */
    boolean exists(Long userId, Long attractionId);

    /**
     * 특정 사용자가 특정 명소를 즐겨찾기 추가
     */
    void add(Long userId, Long attractionId);

    /**
     * 특정 사용자가 특정 명소 즐겨찾기 해제
     */
    void remove(Long userId, Long attractionId);
    
    List<Bookmark> getAllByUser(Long userId);
}
