package com.tia102g3.product.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tia102g3.shoppingcart.model.ShoppingCartVO;
import com.tia102g3.systemcourse.model.SystemCourse;
import com.tia102g3.systemcourse.model.SystemCourseLevel;

@Service("productService")
public class ProductService {

	@Autowired
	ProductRepository repository;

	public void insertProduct(ProductVO productVO) {

		repository.save(productVO);
	}

	public void updateProduct(ProductVO productVO) {

		repository.save(productVO);
	}

	public void deleteProduct(Integer productVO) {
		repository.deleteById(productVO);
	}

	public ProductVO findByPrimaryKey(Integer productID) {
		Optional<ProductVO> optional = repository.findById(productID);
//	      return optional.get();
		return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
	}

	public List<ProductVO> getAll() {
		return repository.findAll();
	}

//    @Override
    @Transactional
	public List<ProductVO> getProductList(String keyword, Integer pageNo) {
        List<Object[]> rawData = repository.getProductList(keyword, pageNo);
        System.out.println(rawData.size());
        return rawData.stream()
                .map(this::convertToProduct)
                .toList();
	}
    private ProductVO convertToProduct(Object[] row) {
    	ProductVO productVO = new ProductVO();
    	productVO.setProductID((Integer) row[0]);
    	productVO.setProdName((String) row[1]);
    	productVO.setProductPic((byte[]) row[2]);
    	productVO.setPrice((Integer) row[3]);
    	productVO.setProductQuantity((Integer) row[4]);
    	productVO.setIntro((String) row[5]);
        return productVO;
    }

	public Long getProductCount(String keyword) {
		return repository.getProductCount(keyword);
	}
}
