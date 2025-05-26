package com.trip.viewlog.review.application.outputport;

import java.util.List;

public interface ReviewGptService {
    String getSummary(String contentType, List<String> reviews);
}
