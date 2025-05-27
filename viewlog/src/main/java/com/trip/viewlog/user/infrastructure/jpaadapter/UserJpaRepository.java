package com.trip.viewlog.user.infrastructure.jpaadapter;

import com.trip.viewlog.user.infrastructure.jpaadapter.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByOauthInfo(String oauthInfo);
}
