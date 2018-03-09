package com.immoc.order.repository;

import com.immoc.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaozefeng
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
