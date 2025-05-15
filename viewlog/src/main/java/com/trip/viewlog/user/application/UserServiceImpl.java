package com.trip.viewlog.user.application;

import com.trip.viewlog.user.application.outputport.UserRepository;
import com.trip.viewlog.user.controller.inputport.UserService;
import com.trip.viewlog.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
