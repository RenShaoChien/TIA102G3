package com.tia102g3.orderdetails.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Entity
@Table(name = "order_details")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsVO implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	private Integer ordDtIID;
	private Integer productID;
	private Integer quantity;
	private Integer orderID;
}
