package com.immoc.order.repository;

import com.immoc.order.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xiaozefeng
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

}
