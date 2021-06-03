package com.assignment.oms.controller;

import com.assignment.oms.dto.*;
import com.assignment.oms.model.CoreAttributes;
import com.assignment.oms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders/")
public class OrdersController {

    @Autowired
    OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<OrderDetails>> getOrders(@RequestAttribute("coreAttributes") CoreAttributes coreAttributes) {
        return ResponseEntity.ok(
                orderService.getOrders(coreAttributes)
        );
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetails> getOrderDetails(@PathVariable Long orderId, @RequestAttribute("coreAttributes") CoreAttributes coreAttributes) {
        return ResponseEntity.ok(
                orderService.getOrderDetails(orderId, coreAttributes)
        );
    }

    @PostMapping("/")
    public ResponseEntity<OrderResponse> create(@RequestBody CreateOrderRequest request, @RequestAttribute("coreAttributes") CoreAttributes coreAttributes) {
        return new ResponseEntity<>(orderService.createOrder(request, coreAttributes), HttpStatus.OK);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderResponse> update(@RequestBody UpdateOrderRequest request, @PathVariable Long orderId, @RequestAttribute("coreAttributes") CoreAttributes coreAttributes) {
        return new ResponseEntity<>(orderService.updateOrder(request, orderId, coreAttributes), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<OrderResponse> delete(@PathVariable Long orderId, @RequestAttribute("coreAttributes") CoreAttributes coreAttributes) {
        return new ResponseEntity<>(orderService.deleteOrder(orderId, coreAttributes), HttpStatus.OK);
    }
}
