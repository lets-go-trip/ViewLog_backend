package com.trip.viewlog.user.controller;

import com.trip.viewlog.user.controller.inputport.UserService;
import com.trip.viewlog.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getById(@PathVariable("user_id") Long userId) {
        return ResponseEntity.ok(userService.getById(userId));
    }
}
