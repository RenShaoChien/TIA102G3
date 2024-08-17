package com.controllers.mark;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

//    @PostMapping("/saveOrder")
//    public ResponseEntity<String> saveOrder(@RequestParam("memberID") String memberID,
//                                            @RequestBody List<ProductInfo> productList) {
//        if (memberID == null || memberID.isEmpty()) {
//            return ResponseEntity.badRequest().body("Error: Member ID cannot be null or empty");
//        }
//
//        redisService.saveProductList(memberID, productList);
//        return ResponseEntity.ok("Product list saved successfully for member ID: " + memberID);
//    }
//
//    @GetMapping("/getOrder")
//    public ResponseEntity<List<ProductInfo>> getOrder(@RequestParam("memberID") String memberID) {
//        List<ProductInfo> productList = redisService.getProductList(memberID);
//        if (productList == null || productList.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(productList);
//    }
    
    @PostMapping("/saveOrder")
    public ResponseEntity<String> saveOrder(@RequestBody MemberOrder orderRequest) {
        String memberID = orderRequest.getMemberID();
        System.out.println( "memberID: " + memberID);
        List<ProductInfo> productList = orderRequest.getProductList();
        
        if (memberID == null || memberID.isEmpty()) {
            return ResponseEntity.badRequest().body("Error: Member ID cannot be null or empty");
        }

        redisService.saveProductList(memberID, productList);
        return ResponseEntity.ok("Product list saved successfully for member ID: " + memberID);
    }
    
    @GetMapping("/test")
    public void test() {
        String key = "1234";
        List<ProductInfo> productList = redisService.getProductList(key);
        
//        for(ProductInfo pinfo : productList) {
//            System.out.print("ID: " + pinfo.getId() + ",");
//            System.out.print("Name: " + pinfo.getName() + ",");
//            System.out.print("Quantity: " + pinfo.getQuantity() + ",");
//            System.out.println("Price: " + pinfo.getPrice());
//            
//        }
        
        System.out.println("Get list test");
        
    }

}


