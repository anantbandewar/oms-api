package com.assignment.oms.service;

import com.assignment.oms.domain.OrderItem;
import com.assignment.oms.dto.OrderItemDetails;
import com.assignment.oms.dto.OrderItemRequest;
import com.assignment.oms.repository.OrderItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ObjectMapper mapper;


    @Override
    public List<OrderItemDetails> getOrderItems(Long orderId) {
        List<OrderItemDetails> response = new ArrayList<>();

        try {
            List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
            response = orderItems.stream()
                    .map(orderItem -> mapper.convertValue(orderItem, OrderItemDetails.class))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public Iterable<OrderItem> saveOrderItems(List<OrderItemRequest> orderItemRequests, Long orderId) {
        Iterable<OrderItem> response = null;
        try {
            List<OrderItem> orderItems = orderItemRequests.stream()
                    .map(orderItemRequest -> populateOrderItem(orderItemRequest, orderId))
                    .collect(Collectors.toList());

            response = orderItemRepository.saveAll(orderItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    private OrderItem populateOrderItem(OrderItemRequest orderItemRequest, Long orderId) {
        return OrderItem.builder()
                .orderId(orderId)
                .productId(orderItemRequest.getProductId())
                .quantity(orderItemRequest.getQuantity())
                .build();
    }
}
