package com.app.f1x.controller;

import com.app.f1x.dto.CreateOrderRequest;
import com.app.f1x.model.Customer;
import com.app.f1x.model.Order;
import com.app.f1x.repository.CustomerRepository;
import com.app.f1x.repository.OrderRepository;
import com.app.f1x.util.OrderMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;

    public OrderController(OrderRepository orderRepository, CustomerRepository customerRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        Optional<Order> order = orderRepository.getById(id);
        return order.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        Optional<Customer> customerOpt = customerRepository.getById(request.getCustomerId());
        if (customerOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid customer ID");
        }

        Customer customer = customerOpt.get();
        Order order = orderMapper.toOrder(request, customer);

        orderRepository.create(order);
        return ResponseEntity.ok("Order created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable int id, @Valid @RequestBody Order order) {
        Optional<Order> existingOrder = orderRepository.getById(id);
        if (existingOrder.isPresent()) {
            orderRepository.update(id, order);
            return ResponseEntity.ok("Order updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id) {
        Optional<Order> existingOrder = orderRepository.getById(id);
        if (existingOrder.isPresent()) {
            orderRepository.delete(id);
            return ResponseEntity.ok("Order deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
