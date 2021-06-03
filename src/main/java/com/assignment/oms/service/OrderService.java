package com.assignment.oms.service;

import com.assignment.oms.dto.*;
import com.assignment.oms.model.CoreAttributes;

import java.util.List;

public interface OrderService {

    List<OrderDetails> getOrders(CoreAttributes coreAttributes);

    OrderDetails getOrderDetails(Long orderId, CoreAttributes coreAttributes);

    OrderResponse createOrder(CreateOrderRequest request, CoreAttributes coreAttributes);

    OrderResponse updateOrder(UpdateOrderRequest request, Long orderId, CoreAttributes coreAttributes);

    OrderResponse deleteOrder(Long orderId, CoreAttributes coreAttributes);
}
