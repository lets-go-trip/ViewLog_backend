package com.trip.viewlog.user.domain;

import com.trip.viewlog.global.dto.response.OAuth2Response;
import lombok.Builder;
import lombok.Getter;

@Getter
public class User {
    private final Long id;
    private final String role;
    private final String oauthInfo;
    private final String name;
    private final String email;

    @Builder
    public User(Long id, String role , String oauthInfo, String name, String email) {
        this.id = id;
        this.role = role;
        this.oauthInfo = oauthInfo;
        this.name = name;
        this.email = email;
    }

    public static User from(String oauthInfo, OAuth2Response oAuth2Response) {
        return User.builder()
                .role("ROLE_USER")
                .oauthInfo(oauthInfo)
                .name(oAuth2Response.getName())
                .email(oAuth2Response.getEmail())
                .build();
    }

    public User update(OAuth2Response oAuth2Response) {
        return User.builder()
                .id(id)
                .role(role)
                .oauthInfo(oauthInfo)
                .name(oAuth2Response.getName())
                .email(oAuth2Response.getEmail())
                .build();
    }
}
