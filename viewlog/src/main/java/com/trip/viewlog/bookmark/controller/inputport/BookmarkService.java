package com.trip.viewlog.bookmark.controller.inputport;

import com.trip.viewlog.user.domain.User;

public interface BookmarkService {
    /**
     * 특정 사용자가 특정 명소를 즐겨찾기했는지 여부 확인
     */
    boolean exists(User user, Long attractionId);

    /**
     * 특정 사용자가 특정 명소를 즐겨찾기 추가
     */
    void add(User user, Long attractionId);

    /**
     * 특정 사용자가 특정 명소 즐겨찾기 해제
     */
    void remove(User user, Long attractionId);
}
