package com.controllers.mark;

import java.util.List;

public class MemberOrder {
    private String memberID;
    private List<ProductInfo> productList;

    // Getters and setters
    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public List<ProductInfo> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductInfo> productList) {
        this.productList = productList;
    }
    
}
