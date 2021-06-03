package com.assignment.oms.service;

import com.assignment.oms.domain.OrderItem;
import com.assignment.oms.dto.OrderItemDetails;
import com.assignment.oms.dto.OrderItemRequest;

import java.util.List;

public interface OrderItemService {

    List<OrderItemDetails> getOrderItems(Long orderId);

    Iterable<OrderItem> saveOrderItems(List<OrderItemRequest> orderItemRequests, Long orderId);
}
