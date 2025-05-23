package com.trip.viewlog.attraction.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContentType {
    private final Integer id;
    private final String name;
}
