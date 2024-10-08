package com.tia102g3.product.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service("productService")
public class ProductService {

	@Autowired
	ProductRepository repository;

	public void deleteProduct(Integer productID) {
		repository.deleteById(productID);
	}

	public Long getProductCount(String keyword) {
		return repository.getProductCount(keyword);
	}

	@Transactional
	public void updateProduct(ProductVO pd, MultipartFile productPic) throws IOException {
		pd.setProductPic(productPic.getBytes());
		repository.save(pd);
	}

	public ProductVO findProductById(Integer productID) {
		return repository.getReferenceById(productID);
	}

	public List<ProductVO> getProductList(String keyword, int offset) {
		List<Object[]> productObjList = repository.getProductList(keyword, offset);
		return productObjList.stream().map(this::convertToProductVO).toList();
	}

	private ProductVO convertToProductVO(Object[] row) {
		ProductVO pd = new ProductVO();
        pd.setProductID((Integer) row[0]);
        pd.setProdName((String) row[1]);
        pd.setPrice((Integer) row[2]);
        pd.setProductQuantity((Integer) row[3]);
        pd.setIntro((String) row[4]);
        pd.setProductPic((byte[]) row[5]);
        return pd;
	}

	public List<ProductVO> getAll() {
        System.out.println("ProductService: 開始調用 repository.findAll()");
        List<ProductVO> products = repository.findAll();
        System.out.println("ProductService: findAll() 返回的產品數量: " + products.size());

        if (products.isEmpty()) {
            System.out.println("ProductService: 警告 - 沒有找到任何產品");
        } else {
            System.out.println("ProductService: 第一個產品信息: " + products.get(0));
        }

        return products;
    }

	public ProductVO findById(Integer productID) {
		// TODO Auto-generated method stub
		return repository.getById(productID);
	}


//	public byte[] getProductPic(Integer productID) {
//		// 使用Repository获取ProductVO
//        Optional<ProductVO> productOptional = repository.findById(productID);
//        // 如果Product存在，则返回图片，否则返回null
//        return productOptional.map(ProductVO::getProductPic).orElse(null);
//
//	}
}
