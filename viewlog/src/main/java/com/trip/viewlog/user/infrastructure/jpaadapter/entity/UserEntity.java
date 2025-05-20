package com.trip.viewlog.user.infrastructure.jpaadapter.entity;

import com.trip.viewlog.user.domain.User;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    private String oauthInfo;

    private String name;

    private String email;

    public static UserEntity from(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.id = user.getId();
        userEntity.role = user.getRole();
        userEntity.oauthInfo = user.getOauthInfo();
        userEntity.name = user.getName();
        userEntity.email = user.getEmail();
        return userEntity;
    }

    public User toModel() {
        return User.builder()
                .id(id)
                .role(role)
                .oauthInfo(oauthInfo)
                .name(name)
                .email(email)
                .build();
    }
}
