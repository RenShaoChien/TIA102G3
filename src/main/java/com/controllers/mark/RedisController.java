package com.controllers.mark;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

//    @PostMapping("/save")
//    public void saveData(@RequestParam String key, @RequestParam String value) {
//        redisService.save(key, value);
//        System.out.println("Save success!");
//    }
    
    @PostMapping("/save")
    public void saveData(@RequestBody Map<String, String> data) {
        // 从 JSON 请求体中提取 id 和 quantity
        String id = data.get("id");
        String quantity = data.get("quantity");

        // 假设你使用的是 redisService.save(key, value)
        redisService.save(id, quantity);
        System.out.println("id: " + id);
        System.out.println("quantity: " + quantity);
        System.out.println("Save success!");
    }
    

    @GetMapping("/get")
    public Object getData(@RequestParam String key) {
        return redisService.get(key);
    }

    @DeleteMapping("/delete")
    public void deleteData(@RequestParam String key) {
        redisService.delete(key);
    }
}
