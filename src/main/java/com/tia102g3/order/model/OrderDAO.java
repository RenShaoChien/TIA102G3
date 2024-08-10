package com.tia102g3.order.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClassName： OrderDAO
 * package：com.tia102g3.order.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/8/10 上午5:49
 * @Version 1.0
 */
public interface OrderDAO extends JpaRepository<OrderVO, Integer> {
}
