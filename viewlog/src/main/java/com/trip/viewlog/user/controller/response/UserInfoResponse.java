package com.trip.viewlog.user.controller.response;

import com.trip.viewlog.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoResponse {
    @Schema(description = "유저 이름", example = "홍길동")
    private String name;

    @Schema(description = "유저 이메일", example = "abcd@gmail.com")
    private String email;

    public static UserInfoResponse from(User user) {
        return UserInfoResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
