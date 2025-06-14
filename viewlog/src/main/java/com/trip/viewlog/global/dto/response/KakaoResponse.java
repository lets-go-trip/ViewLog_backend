package com.trip.viewlog.global.dto.response;

import java.util.Map;

public class KakaoResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    public KakaoResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        Map<String, Object> response = (Map<String, Object>) attribute.get("kakao_account");
        return response.get("email").toString();
    }

    @Override
    public String getName() {
        return ((Map) attribute.get("properties")).get("nickname").toString();
    }
}
