package com.tia102g3.productPic.model;
import java.sql.Date;

public class Product_PICVO implements java.io.Serializable{
	private Integer productPicID;
	private Integer productID;
	private byte[] pic;

	
	public Integer getProductPicID() {
		return productPicID;
	}
	public void setProductPicID(Integer productPicID) {
		this.productPicID = productPicID;
	}
	public Integer getProductID() {
		return productID;
	}
	public void setProductID(Integer productID) {
		this.productID = productID;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}

}
