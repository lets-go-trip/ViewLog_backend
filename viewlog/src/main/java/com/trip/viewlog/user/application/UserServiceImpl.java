package com.trip.viewlog.user.application;

import com.trip.viewlog.user.application.outputport.UserRepository;
import com.trip.viewlog.user.controller.inputport.UserService;
import com.trip.viewlog.user.domain.User;
import com.trip.viewlog.user.infrastructure.jpaadapter.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getByOauthInfo(String oauthInfo) {
        return userRepository.findByOauthInfo(oauthInfo).orElse(null);
    }
}
