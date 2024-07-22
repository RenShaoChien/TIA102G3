package com.tia102g3.product.model;

import java.util.List;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductJDBCDAO();
	}

	public ProductVO insertProduct(String prodName, Integer price, Integer productQuantity, String intro) {

		ProductVO productVO = new ProductVO();

		productVO.setProdName(prodName);
		productVO.setPrice(price);
		productVO.setProductQuantity(productQuantity);
		productVO.setIntro(intro);
		dao.insert(productVO);

		return productVO;
	}

	public ProductVO updateProduct(Integer productID, String prodName, Integer price, Integer productQuantity, String intro) {

		ProductVO productVO = new ProductVO();

		productVO.setProdName(prodName);
		productVO.setPrice(price);
		productVO.setProductQuantity(productQuantity);
		productVO.setIntro(intro);
		productVO.setProductID(productID);
		dao.update(productVO);

		return productVO;
	}

	public void deleteProduct(Integer productVO) {
		dao.delete(productVO);
	}

	public ProductVO findByPrimaryKey(Integer productID) {
		return dao.findByPrimaryKey(productID);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
}
