package com.tia102g3.orderdetails.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


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
	@Column(name = "ordDtIID")
	private Integer ordDtIID;
	private Integer productID;
	private Integer quantity;
	private Integer orderID;
}
