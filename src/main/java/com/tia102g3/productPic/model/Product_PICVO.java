package com.tia102g3.productPic.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name="product_pic")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Product_PICVO implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
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
