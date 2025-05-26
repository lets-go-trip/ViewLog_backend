package com.trip.viewlog.user.infrastructure.jpaadapter;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trip.viewlog.user.infrastructure.jpaadapter.entity.UserEntity;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByOauthInfo(String oauthInfo);
}
