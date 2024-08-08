package com.tia102g3.productPic.model;

import java.util.List;

public interface Product_PICDAO_interface {
          public void insert(Product_PICVO product_PICVO);
          public void update(Product_PICVO product_PICVO);
          public void delete(Integer productPicID);
          public Product_PICVO findByPrimaryKey(Integer product_PICID);
          public List<Product_PICVO> getAll();
        //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
