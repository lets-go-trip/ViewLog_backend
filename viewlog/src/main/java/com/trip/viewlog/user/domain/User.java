package com.trip.viewlog.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private Long id;
    private String name;
    private String email;

    @Builder
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
