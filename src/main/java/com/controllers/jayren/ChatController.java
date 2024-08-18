package com.controllers.jayren;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName： ChatController
 * package：com.controllers.jayren
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/8/18 上午5:50
 * @Version 1.0
 */
@Controller
public class ChatController {

    private final String API_URL = "https://api.openai.com/v1/chat/completions";
    private final String API_KEY = "";

    @PostMapping("/api/ask")
    @ResponseBody
    public ResponseEntity<Map<String, String>> askQuestion(@RequestBody Map<String, String> requestBody) {
        String question = requestBody.get("question");

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", Collections.singletonList(
                Map.of("role", "user", "content", question)
        ));
        body.put("max_tokens", 150);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    API_URL,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            String aiResponse = response.getBody().get("choices").toString();
            Map<String, String> result = new HashMap<>();
            result.put("response", aiResponse);

            return ResponseEntity.ok(result);

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
                return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                        .body(Map.of("error", "請求過多，請稍後再試。"));
            } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST && e.getResponseBodyAsString().contains("insufficient_quota")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "API 配額已用完，請檢查您的計劃和帳單詳情。"));
            } else {
                return ResponseEntity.status(e.getStatusCode())
                        .body(Map.of("error", "發生錯誤：" + e.getMessage()));
            }
        }
    }

    @PostMapping("/api/getWeightLossAdvice")
    @ResponseBody
    public Map<String, String> getWeightLossAdvice(@RequestBody Map<String, String> requestBody) {
        String loseWeight = requestBody.get("loseWeight");
        String courseName = requestBody.get("courseName");
        String burnCalories = requestBody.get("burnCalories");
        String rpsOrEachExerciseTime = requestBody.get("rpsOrEachExerciseTime");
        String swpOrSportTime = requestBody.get("swpOrSportTime");
        String coachCourse = requestBody.get("coachCourse");

        // 构建 OpenAI 的 Prompt
        String prompt = "使用者計劃通過 " + courseName + " 來減重 " + loseWeight + " 公斤。課程安排如下：" +
                "每次運動時長為 " + rpsOrEachExerciseTime + "，每組運動時間為 " + swpOrSportTime + "，預計消耗卡路里約為 " + burnCalories + "。" +
                "請直接提供最有效的減重建議，包括具體的飲食方案、鍛鍊頻率，以及任何其他對使用者達成目標至關重要的建議。" +
                "如有適合的教練課程（如：" + coachCourse + "），請簡要推薦並說明其優勢。" +
                "請只提供關鍵資訊，集中於實現目標的最佳方法。只需提供3條最重要的減重建議：1. 飲食方案；2. 鍛鍊頻率；3. 其他重要建議；4.如何達成減重目標公斤數。不需要任何解釋或背景信息。";

        // 构建请求的主体
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");

        Map<String, Object> body = new HashMap<>();
        body.put("model", "gpt-3.5-turbo");
        body.put("messages", Collections.singletonList(
                Map.of("role", "user", "content", prompt)
        ));
        body.put("max_tokens", 2000);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                entity,
                Map.class
        );

        String aiResponse = response.getBody().get("choices").toString();

        // 使用正則表達式過濾掉不需要的部分
        aiResponse = aiResponse.replaceAll("\\b(index=\\d+|message=|role=assistant|refusal=null|logprobs=null|finish_reason=stop)\\b", ""); // 去除多餘的關鍵字
        aiResponse = aiResponse.replaceAll("[\\[\\]{}]", "");  // 去除括號
        aiResponse = aiResponse.replaceAll(",\\s+", "，"); // 替換英文逗號為全形逗號，並去除多餘的空格
        aiResponse = aiResponse.replaceAll("\\.\\s*", "。"); // 替換英文句號為全形句號，並去除多餘的空格

        // 手動替換數字為全形
        StringBuilder fullWidthNumbers = new StringBuilder();
        for (char c : aiResponse.toCharArray()) {
            if (Character.isDigit(c)) {
                fullWidthNumbers.append((char) ('０' + (c - '0'))); // 將數字替換為全形數字
            } else {
                fullWidthNumbers.append(c); // 保留非數字字符
            }
        }
        aiResponse = fullWidthNumbers.toString();

        aiResponse = aiResponse.replaceAll("，\\s*，\\s*", "，"); // 去除多餘的全形逗號
        aiResponse = aiResponse.trim();  // 去除前後多餘的空格

        Map<String, String> result = new HashMap<>();
        result.put("advice", aiResponse);

        return result;
    }

}
