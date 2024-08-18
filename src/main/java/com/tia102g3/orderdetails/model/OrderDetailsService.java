package com.tia102g3.orderdetails.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tia102g3.order.model.OrderVO;
import com.tia102g3.product.model.ProductVO;


//import hibernate.util.CompositeQuery.HibernateUtilCompositeQuery_order_details;


@Service("orderDetailsService")
public class OrderDetailsService{

	@Autowired
	OrderDetailsRepository repository;

	@Autowired
	private SessionFactory sessionFactory;

//	public void addOrderDetails(OrderDetailsVO orderDetailsVO) {
//		repository.save(orderDetailsVO);
//	}

	public void deleteOrderDetails(Integer ordDtlID) {
		repository.deleteById(ordDtlID);
	}
	
	public Long getOrderDetailsCount(String keyword) {
		return repository.getOrderDetailsCount(keyword);
	}
	
	public void updateOrderDetails(OrderDetailsVO orderDetailsVO) {
		repository.save(orderDetailsVO);
	}

	public OrderDetailsVO getOneOrderDetails(Integer ordDtlID) {
//		Optional<OrderDetailsVO> optional = repository.findById(ordDtlID);
//		return optional.orElse(null);
		return repository.getReferenceById(ordDtlID);
	}

	public List<OrderDetailsVO> getOrderDetailsList(String keyword, Integer offset){
		List<Object[]> orderDetailsObjList = repository.getOrderDetailsList(keyword, offset);
		return orderDetailsObjList.stream().map(this::convertToOrderDetailsVO).toList();
	}
	
	private OrderDetailsVO convertToOrderDetailsVO(Object[] row) {
		OrderDetailsVO odds = new OrderDetailsVO();
		odds.setOrdDtIID((Integer) row[0]);
		odds.setProductID((Integer) row[1]);
		odds.setQuantity((Integer) row[2]);
		odds.setOrderID((Integer) row[3]);
        return odds;
	}



	public List<OrderDetailsVO> getAll() {
        System.out.println("OrderDetailsService: 開始調用 repository.findAll()");
        List<OrderDetailsVO> orderDetails = repository.findAll();
        System.out.println("OrderDetailsService: findAll() 返回的產品數量: " + orderDetails.size());

        if (orderDetails.isEmpty()) {
            System.out.println("OrderDetailsService: 警告 - 沒有找到任何訂單明細");
        } else {
            System.out.println("OrderDetailsService: 第一個產品信息: " + orderDetails.get(0));
        }

        return orderDetails;
	}
	
	
	public List<OrderDetailsVO> findAllById(Integer orderId) {
//		ArrayList<Integer> list = new ArrayList<>();
//		list.add(orderId);
		return repository.findAllByOrderID(orderId);
	}

	public OrderDetailsVO findOrderDetailsById(Integer ordDtIID) {
		return repository.getReferenceById(ordDtIID);
	}

	public List<OrderDetailsVO> findByOrderID(Integer orderID) {
		// TODO Auto-generated method stub
		return repository.findAllByOrderID(orderID);
	}
	
}

