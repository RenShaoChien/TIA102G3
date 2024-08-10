//package com.tia102g3.productPic.model;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.tia102g3.shoppingcart.model.ShoppingCartVO;
//
//@Service("productpicService")
//public class Product_PICService {
//
//	@Autowired
//	Product_PICRepository repository;
//
//	public void insertProductPic(Product_PICVO productpicVO) {
//
//		repository.save(productpicVO);
//	}
//
//	public void updateProductPic(Product_PICVO productpicVO) {
//
//		repository.save(productpicVO);
//	}
//
//	public void deleteProductPic(Integer productpicVO) {
//		repository.deleteById(productpicVO);
//	}
//
//	public Product_PICVO findByPrimaryKey(Integer productPicID) {
//		Optional<Product_PICVO> optional = repository.findById(productPicID);
////	      return optional.get();
//		return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
//	}
//
//	public List<Product_PICVO> getAll() {
//		return repository.findAll();
//	}
//}
