package com.tia102g3.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tia102g3.member.model.Member;
import com.tia102g3.product.model.ProductVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "shopping_cart")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartVO implements java.io.Serializable{
	
	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer shoppingCartID;
	
	@ManyToOne
	@JoinColumn(name = "memberID", referencedColumnName = "memberID", nullable = false)
	private Member memberID;
	
	@ManyToOne
    @JoinColumn(name = "productID", referencedColumnName = "productID", nullable = false)
	private ProductVO productID;
	
	private Integer quantity;
	

}
