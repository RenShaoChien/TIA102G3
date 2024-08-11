package com.tia102g3.product.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
	public void updateProduct(ProductVO pd, MultipartFile productImage) throws IOException {
		pd.setProductPic(productImage.getBytes());
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
		return repository.findAll();
	}
}
