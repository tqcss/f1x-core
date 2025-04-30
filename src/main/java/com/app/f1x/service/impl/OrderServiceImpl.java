//package com.app.f1x.service.impl;
//
//import com.app.f1x.dto.CreateOrderDTO;
//import com.app.f1x.dto.OrderResponseDTO;
//import com.app.f1x.dto.UpdateOrderDTO;
//import com.app.f1x.model.Order;
//import com.app.f1x.model.OrderItem;
//import com.app.f1x.repository.OrderRepository;
//import com.app.f1x.service.OrderService;
//import com.app.f1x.util.mapper.OrderMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class OrderServiceImpl implements OrderService {
//
//    private final OrderRepository orderRepository;
//    private final OrderMapper orderMapper;
//
//    @Autowired
//    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper) {
//        this.orderRepository = orderRepository;
//        this.orderMapper = orderMapper;
//    }
//
//    @Override
//    public List<OrderResponseDTO> getAllOrders() {
//        return orderRepository.findAll().stream()
//                .map(orderMapper::toOrderResponseDTO)
//                .toList();
//    }
//
//    @Override
//    public Optional<OrderResponseDTO> getOrderById(int id) {
//        return orderRepository.findById(id).map(orderMapper::toOrderResponseDTO);
//    }
//
//    @Override
//    @Transactional
//    public OrderResponseDTO createOrder(CreateOrderDTO createOrderDTO) {
//        Order order = orderMapper.toOrder(createOrderDTO);
//        orderRepository.save(order);
//        return orderMapper.toOrderResponseDTO(order);
//    }
//
//    @Override
//    @Transactional
//    public Optional<OrderResponseDTO> updateOrder(Long id, UpdateOrderDTO updateOrderDTO) {
//        Optional<Order> existingOrder =  orderRepository.findById(Math.toIntExact(id))
//                .map(order -> {
//                    return orderMapper.toOrder(updateOrderDTO, order);
//                });
//
//        if (existingOrder.isPresent()) {
//            orderRepository.save(existingOrder.get());
//            return Optional.ofNullable(orderMapper.toOrderResponseDTO(existingOrder.get()));
//        }
//    }
//
//    @Override
//    public void deleteOrder(Long id) {
//
//    }
//
//    @Override
//    public Optional<OrderResponseDTO> getOrderById(Integer id) {
//        return Optional.empty();
//    }
//
//}
