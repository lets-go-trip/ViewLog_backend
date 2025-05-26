package com.trip.viewlog.review.infrastructure.gptadapter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.viewlog.review.application.outputport.ReviewGptService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewGptServiceImpl implements ReviewGptService {

    private final ChatClient openAiChatClient;

    @Override
    public String getSummary(String contentType, List<String> reviews) {
        String guidance = getCategoryGuidance(contentType);

        String systemText = String.format("""
                1. 당신은 여행지 리뷰를 분석하는 AI입니다. 현재 입력으로 주어지는 리뷰의 장소 카테고리는 '%s'이며, 이 카테고리에 적합한 관점은 다음과 같습니다:
                  - %s
                
                2. 리뷰를 분석해 아래와 같은 JSON 형식으로만 응답하세요. 설명은 하지 마세요. JSON 외의 문자는 절대 포함하지 마세요 :
                
                {
                  "contentType": "<카테고리명>",
                  "summary": "<리뷰들의 요약 내용>"
                }
                
                3. 리뷰는 아래와 같은 형식으로 사용자가 입력합니다.
                - 경치가 정말 아름다워요. 사진 찍기 좋아요!
                - 사람이 너무 많아서 좀 불편했어요.
                - 주차 공간이 협소했지만 직원이 친절했어요.
               
                4. 주의사항:
                - 과도하게 감정적이거나 극단적인 표현이 포함된 리뷰는 제외하고 분석하세요.
                - 응답은 반드시 위 JSON 포맷으로만 출력하세요.
                - 문장은 중립적이고 명확한 어조로 작성하세요.
                - 요약 내용은 3줄이내로 작성하세요.
                """, contentType, guidance);

        String userText = reviews.stream()
                .filter(r -> r != null && !r.isBlank())
                .map(r -> "- " + r.trim())
                .collect(Collectors.joining("\n"));

        String result = openAiChatClient.prompt().system(systemText).user(userText).call().content();
        String cleaned = result
                .replaceAll("(?s)```json", "")
                .replaceAll("```", "")
                .trim();
        return extractSummary(cleaned);
    }

    private String getCategoryGuidance(String contentType) {
        return switch (contentType) {
            case "관광지" -> "전반적인 만족도, 볼거리의 다양성과 접근성에 집중";
            case "문화시설" -> "시설의 청결, 프로그램의 질, 관람 편의성에 집중";
            case "축제공연행사" -> "공연이나 행사 구성, 재미, 진행 방식에 집중";
            case "여행코스" -> "이동 동선의 효율성, 구성의 알차고 유익한지에 집중";
            case "레포츠" -> "활동의 재미, 안전성, 난이도, 장비 상태 등에 집중";
            case "숙박" -> "청결, 침구의 편안함, 소음, 직원 응대에 집중";
            case "쇼핑" -> "상품 다양성, 가격, 직원 친절도, 혼잡도에 집중";
            case "음식점" -> "맛, 위생, 직원 친절도, 가격 대비 만족도에 집중";
            default -> "사용자들이 중요하게 여긴 포인트에 중점";
        };
    }

    public String extractSummary(String gptResponseJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(gptResponseJson);
            return root.get("summary").asText(); // summary 값만 추출
        } catch (Exception e) {
            throw new RuntimeException("GPT 응답 파싱 실패", e);
        }
    }
}
