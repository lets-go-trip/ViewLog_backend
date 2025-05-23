package com.trip.viewlog.global.application;

import com.trip.viewlog.global.dto.CustomOAuth2User;
import com.trip.viewlog.global.dto.response.GoogleResponse;
import com.trip.viewlog.global.dto.response.KakaoResponse;
import com.trip.viewlog.global.dto.response.NaverResponse;
import com.trip.viewlog.global.dto.response.OAuth2Response;
import com.trip.viewlog.user.application.outputport.UserRepository;
import com.trip.viewlog.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("naver")) {

            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else if (registrationId.equals("kakao")) {

            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }

        String oauthInfo = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        User existUser = userRepository.findByOauthInfo(oauthInfo).orElse(null);

        if (existUser == null) {

            User user = userRepository.save(User.from(oauthInfo, oAuth2Response));

            return new CustomOAuth2User(user);
        }
        else {

            existUser.update(oAuth2Response);

            User user = userRepository.save(existUser);

            return new CustomOAuth2User(user);
        }
    }
}