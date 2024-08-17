package com.controllers.mark;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    // 保存產品列表
    public void saveProductList(String memberID, List<ProductInfo> productList) {
        try {
            ListOperations<String, String> listOps = redisTemplate.opsForList();
            for (ProductInfo product : productList) {
                String productJson = objectMapper.writeValueAsString(product);
                listOps.rightPush(memberID, productJson);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    // 獲取產品列表
    public List<ProductInfo> getProductList(String memberID) {
        ListOperations<String, String> listOps = redisTemplate.opsForList();
        List<String> productJsonList = listOps.range(memberID, 0, -1);

        List<ProductInfo> productList = new ArrayList<>();
        for (String productJson : productJsonList) {
            try {
                ProductInfo product = objectMapper.readValue(productJson, ProductInfo.class);
                productList.add(product);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return productList;
    }
    
    // 刪除產品列表
    public void deleteProductList(String memberID) {
        redisTemplate.delete(memberID);
    }
    
}


