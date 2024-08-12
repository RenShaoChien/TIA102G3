package com.controllers.admincontrollers.adminjayren;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName： EditFrontEndPage
 * package：com.controllers.admincontrollers.adminjayren
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/8/11 上午1:21
 * @Version 1.0
 */
@RestController
@RequestMapping("/course")
public class EditFrontEndPage {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("/editFrontEndPage")
    public ResponseEntity<String> editFrontEndPage(@RequestBody Map<String, String> requestData) {
        String description = requestData.get("description");

        if (description != null && !description.isEmpty()) {
            redisTemplate.opsForValue().set("description", description);
            return ResponseEntity.ok("保存成功");
        } else {
            return ResponseEntity.badRequest().body("描述不能為空");
        }
    }

    // 從 Redis 中獲取功能介紹
    @GetMapping("/getFrontEndPageDescription")
    public ResponseEntity<String> getFrontEndPageDescription() {
        String description = redisTemplate.opsForValue().get("description");

        if (description != null) {
            return ResponseEntity.ok(description);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/urlContent")
    public ResponseEntity<Map<String, String>> loadUrlContent(@RequestBody Map<String, String> req) throws IOException {
        String url = req.get("url");
        // 使用 Jsoup來抓取網頁內容
        Document doc = Jsoup.connect(url).get();
        String title = doc.title();
        String image = doc.select("meta[property=og:image]").first().attr("content");
        String content = doc.select("meta[property=og:description]").attr("content");

        Map<String, String> response = new HashMap<>();
        response.put("title", title);
        response.put("image", image);
        response.put("content", content);

        return ResponseEntity.ok(response);
    }
}

