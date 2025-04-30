//package com.app.f1x.util.mapper;
//
//import com.app.f1x.dto.*;
//import com.app.f1x.model.Order;
//import com.app.f1x.model.OrderItem;
//import com.app.f1x.model.Product;
//import com.app.f1x.util.enums.OrderStatus;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//
//@Component
//public class OrderMapper {
//
//    private final Logger logger = LoggerFactory.getLogger(OrderMapper.class);
//
//    public OrderResponseDTO toOrderResponseDTO(Order order) {
//        return OrderResponseDTO.builder()
//                .id(order.getId())
//                .creationTime(order.getCreationTime())
//                .completionTime(order.getCompletionTime())
//                .customerName(order.getCustomerName())
//                .customerContact(order.getCustomerContact())
//                .orderStatus(order.getStatus())
//                .note(order.getNote())
//                .grandTotal(order.getGrandTotal())
//                .orderItems(order.getOrderItems().stream()
//                        .map(this::toOrderItemResponseDTO)
//                        .toList())
//                .build();
//    }
//
//    public OrderItemResponseDTO toOrderItemResponseDTO(OrderItem orderItem) {
//        return OrderItemResponseDTO.builder()
//                .id(orderItem.getId())
//                .serviceName(orderItem.getServiceName())
//                .serviceCost(orderItem.getServiceCost())
//                .quantity(orderItem.getQuantity())
//                .subTotal(orderItem.getSubtotalCost())
//                .requiredProductIds(orderItem.getRequiredProducts().stream().map(Product::getId).toList())
//                .optionalProductIds(orderItem.getOptionalProducts().stream().map(Product::getId).toList())
//                .build();
//    }
//
//    public Order toOrder(CreateOrderDTO createOrderDTO) {
//        Order order = new Order();
//        order.setCreationTime(LocalDateTime.now());
//        order.setCompletionTime(createOrderDTO.getCompletionTime());
//        order.setCustomerName(createOrderDTO.getCustomerName());
//        order.setCustomerContact(createOrderDTO.getCustomerContact());
//        order.setStatus(OrderStatus.PENDING);
//        order.setNote(createOrderDTO.getNote());
//        order.setGrandTotal(0f);
//        order.setOrderItems(Collections.emptyList());
//        return order;
//    }
//
//    public Order toOrder(UpdateOrderDTO updateOrderDTO, Order order) {
//        order.setCompletionTime(updateOrderDTO.getCompletionTime());
//        order.setCustomerName(updateOrderDTO.getCustomerName());
//        order.setCustomerContact(updateOrderDTO.getCustomerContact());
//        order.setNote(updateOrderDTO.getNote());
//        order.setOrderItems(Collections.emptyList());
//        return order;
//    }
//
//}
