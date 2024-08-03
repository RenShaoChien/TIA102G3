package com.tia102g3.orderdetails.model;

import com.tia102g3.order.model.OrderVO;
import com.tia102g3.product.model.ProductVO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "order_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class OrderDetailsVO implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ordDtlID", updatable = false)
	@NonNull
	private Integer ordDtlID;
	@ManyToOne
	@JoinColumn(name = "orderID", referencedColumnName = "orderID", nullable = false)
    private OrderVO orderVO;
	@ManyToOne
	@JoinColumn(name = "productID", referencedColumnName = "productID", nullable = false)
    private ProductVO productVO;
	private Integer quantity;


}
