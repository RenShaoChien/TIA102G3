package com.tia102g3.order.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tia102g3.member.model.Member;
import com.tia102g3.product.model.ProductRepository;
import com.tia102g3.product.model.ProductVO;

@Service("orderService")
public class OrderService {
	
	@Autowired
	OrderRepository repository;

	public List<OrderVO> getOrdertList(String keyword, int offset) {
		List<Object[]> orderObjList = repository.getProductList(keyword, offset);
		return orderObjList.stream().map(this::convertToOrderVO).toList();
	}
	
	private OrderVO convertToOrderVO(Object[] row) {
		OrderVO ov = new OrderVO();
		Member member = new Member();
        ov.setOrderID((Integer) row[0]);
        member.setMemberID((Integer) row[1]);
        ov.setMember(member);
        ov.setOrderDate((java.sql.Date) row[2]);
        ov.setStatus((String) row[3]);
        ov.setTotalPrice((Integer) row[4]);
        return ov;
	}

	public Integer getOrderCount(String keyword) {
		return repository.getOrderCount(keyword);
	}

}
