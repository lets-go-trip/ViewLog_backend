package com.trip.viewlog.user.controller.inputport;

import com.trip.viewlog.user.domain.User;

public interface UserService {
    User getByOauthInfo(String oauthInfo);
}
