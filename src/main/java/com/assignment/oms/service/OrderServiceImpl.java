package com.assignment.oms.service;

import com.assignment.oms.constants.Message;
import com.assignment.oms.constants.OrderStatus;
import com.assignment.oms.constants.UserRole;
import com.assignment.oms.domain.Orders;
import com.assignment.oms.dto.OrderDetails;
import com.assignment.oms.dto.*;
import com.assignment.oms.model.CoreAttributes;
import com.assignment.oms.model.EmailRequest;
import com.assignment.oms.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    ObjectMapper mapper;

    @Value("${email.from}")
    private String emailFrom;


    @Override
    public List<OrderDetails> getOrders(CoreAttributes coreAttributes) {
        List<OrderDetails> response = null;
        try {
            if (UserRole.ADMINISTRATOR.toString().equalsIgnoreCase(coreAttributes.getRole())) {
                List<Orders> orders = (List<Orders>) orderRepository.findAll();
                response = orders.stream()
                        .map(order -> populateOrderDetails(order))
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public OrderDetails getOrderDetails(Long orderId, CoreAttributes coreAttributes) {
        OrderDetails response = null;
        try {
            Optional<Orders> orders = orderRepository.findById(orderId);

            if (orders.isPresent() && (UserRole.ADMINISTRATOR.toString().equalsIgnoreCase(coreAttributes.getRole())
                            || coreAttributes.getUserId().equals(orders.get().getUserId()))) {
                response = populateOrderDetails(orders.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public OrderResponse createOrder(CreateOrderRequest request, CoreAttributes coreAttributes) {
        OrderResponse response = null;

        try {
            Orders orders = orderRepository.save(Orders.builder()
                    .status(OrderStatus.PLACED.toString())
                    .addressId(request.getAddressId())
                    .userId(coreAttributes.getUserId())
                    .userCreated(coreAttributes.getUserId())
                    .dateCreated(Timestamp.from(Instant.now()))
                    .build());

            orderItemService.saveOrderItems(request.getOrderItems(), orders.getOrderId());
            UserDetails userDetails = userService.getUserDetails(coreAttributes.getUserId());
            sendEmailNotification(userDetails, orders.getOrderId());

            response = OrderResponse.builder()
                    .orderId(orders.getOrderId())
                    .orderStatus(orders.getStatus())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public OrderResponse updateOrder(UpdateOrderRequest request, Long orderId, CoreAttributes coreAttributes) {
        OrderResponse response = null;
        try {
            Optional<Orders> orders = orderRepository.findById(orderId);
            if (orders.isPresent() && OrderStatus.of(request.getStatus()) != null
                    && !OrderStatus.CANCELLED.toString().equalsIgnoreCase(orders.get().getStatus())) {
                orders.get().setStatus(request.getStatus());
                orders.get().setUserModified(coreAttributes.getUserId());
                orders.get().setDateModified(Timestamp.from(Instant.now()));

                orderRepository.save(orders.get());
                response = OrderResponse.builder().orderId(orderId).orderStatus(orders.get().getStatus()).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public OrderResponse deleteOrder(Long orderId, CoreAttributes coreAttributes) {
        OrderResponse response = null;
        try {
            Optional<Orders> orders = orderRepository.findById(orderId);
            if (orders.isPresent() && !OrderStatus.CANCELLED.toString().equalsIgnoreCase(orders.get().getStatus())) {
                orders.get().setStatus(OrderStatus.CANCELLED.toString());
                orders.get().setUserModified(coreAttributes.getUserId());
                orders.get().setDateModified(Timestamp.from(Instant.now()));

                orderRepository.save(orders.get());
                response = OrderResponse.builder().orderId(orderId).orderStatus(orders.get().getStatus()).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private OrderDetails populateOrderDetails(Orders orders) {
        UserDetails userDetails = userService.getUserDetails(orders.getUserId());
        List<OrderItemDetails> orderItems = orderItemService.getOrderItems(orders.getOrderId());
        return OrderDetails.builder()
                .user(userDetails)
                .orderItems(orderItems)
                .build();
    }

    private void sendEmailNotification(UserDetails userDetails, Long orderId) {
        try {
            EmailRequest emailRequest = new EmailRequest();
            emailRequest.setFrom(emailFrom);

            emailRequest.setContent("Hi ".concat(userDetails.getFirstName())
                    .concat(" ").concat(userDetails.getLastName())
                    .concat(", <br><br>Your order has been placed successfully!"
                            .concat("<br><br>")
                            .concat("For any concerns please contact ")
                            .concat(emailFrom).concat("<br><br>")));

            emailRequest.setSubject("Order: ".concat(orderId.toString()).concat("-")
                    .concat(new SimpleDateFormat("MM/dd/yyyy").format(Date.from(Instant.now()))));

            emailRequest.setRecipients(Arrays.asList(userDetails.getEmail()));
            emailRequest.setTemplateName(Message.EMAIL_NOTIFICATION);

            emailService.sendMail(emailRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
