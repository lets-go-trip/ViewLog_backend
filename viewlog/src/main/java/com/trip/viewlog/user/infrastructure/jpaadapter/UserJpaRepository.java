package com.trip.viewlog.user.infrastructure.jpaadapter;

import com.trip.viewlog.user.infrastructure.jpaadapter.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
