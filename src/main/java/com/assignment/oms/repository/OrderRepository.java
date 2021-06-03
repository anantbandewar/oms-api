package com.assignment.oms.repository;

import com.assignment.oms.domain.Orders;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Orders, Long> {

}
