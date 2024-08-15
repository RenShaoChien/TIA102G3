package com.tia102g3.order.model;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

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
        List<Object[]> orderObjList = repository.getOrderList(keyword, offset);
        return orderObjList.stream().map(this::convertToOrderVO).toList();
    }

    private OrderVO convertToOrderVO(Object[] row) {
        OrderVO ov = new OrderVO();
        ov.setOrderID((Integer) row[0]);
        ov.setMember(new Member((Integer) row[1]));
        ov.setOrderDate((java.sql.Timestamp) row[2]);
        ov.setStatus((String) row[3]);
        ov.setTotalPrice((Integer) row[4]);
        return ov;
    }

    public Long getOrderCount(String keyword) {
        return repository.getOrderCount(keyword);
    }

	public void updateOrder(@Valid OrderVO ov) throws IOException{
		repository.save(ov);
	}

}
