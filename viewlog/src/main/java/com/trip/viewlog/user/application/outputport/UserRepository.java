package com.trip.viewlog.user.application.outputport;

import com.trip.viewlog.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
}
