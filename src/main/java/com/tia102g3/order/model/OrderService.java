package com.tia102g3.order.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tia102g3.member.model.Member;
import com.tia102g3.orderdetails.model.OrderDetailsService;
import com.tia102g3.orderdetails.model.OrderDetailsVO;
import com.tia102g3.product.model.ProductRepository;
import com.tia102g3.product.model.ProductService;
import com.tia102g3.product.model.ProductVO;

@Service("orderService")
public class OrderService {

	@Autowired
	OrderRepository repository;
	
	@Autowired
	OrderDetailsService orderDetailsService;
	
	@Autowired
	ProductService productService;

	public List<OrderVO> getOrdertList(String keyword, int offset) {
		List<Object[]> orderObjList = repository.getOrderList(keyword, offset);
		return orderObjList.stream().map(this::convertToOrderVO).toList();
	}

//	private OrderVO convertToOrderVO(Object[] row) {
//		OrderVO ov = new OrderVO();
//		ov.setOrderID((Integer) row[0]);
//		ov.setMember(new Member((Integer) row[1]));
//		ov.setOrderDate((java.sql.Timestamp) row[2]);
//		ov.setStatus((String) row[3]);
//		用row[0]調出OrderDetailsID，
//		用OrderDetailsID調出quantity，
//		再用OrderDetails調出productID，
//		再用productID調出Product的Price
//		ov.setTotalPrice((Integer) row[4]);
//		return ov;
//	}
	
	
	private OrderVO convertToOrderVO(Object[] row) {
	    OrderVO ov = new OrderVO();
	    ov.setOrderID((Integer) row[0]);
	    ov.setMember(new Member((Integer) row[1]));
	    ov.setOrderDate((java.sql.Timestamp) row[2]);
	    ov.setStatus((String) row[3]);

	    // 获取 orderID
	    Integer orderID = (Integer) row[0];

	    // 获取该订单的所有订单明细
	    List<OrderDetailsVO> orderDetailsList = orderDetailsService.findByOrderID(orderID);

	    int totalPrice = 0;

	    // 遍历订单明细以计算总价
	    for (OrderDetailsVO orderDetails : orderDetailsList) {
	        // 获取商品ID
	        Integer productID = orderDetails.getProductID();
	        // 获取商品价格
	        ProductVO product = productService.findById(productID);
	        int price = product.getPrice();
	        // 计算总价
	        totalPrice += orderDetails.getQuantity() * price;
	    }

	    // 设置计算后的总价
	    ov.setTotalPrice(totalPrice);

	    return ov;
	}


	public Long getOrderCount(String keyword) {
		return repository.getOrderCount(keyword);
	}

	public void updateOrder(@Valid OrderVO ov) throws IOException {
		repository.save(ov);
	}

	public OrderVO findOrderById(Integer orderID) {
		return repository.getReferenceById(orderID);
	}

	public void save(OrderVO order) {
		repository.save(order);
		
	}

//	public OrderVO findById(int orderID) {
//		return repository.getReferenceById(orderID);
//	}



}
