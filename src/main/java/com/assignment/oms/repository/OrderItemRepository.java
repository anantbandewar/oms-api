package com.assignment.oms.repository;

import com.assignment.oms.domain.OrderItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends PagingAndSortingRepository<OrderItem, Long> {

    @Query(value = "SELECT O FROM dbo.OrderItem O WHERE O.OrderId = :orderId")
    List<OrderItem> findByOrderId(@Param("orderId") Long orderId);
}
