//package com.app.f1x.controller;
//
//import com.app.f1x.dto.OrderRequest;
//import com.app.f1x.dto.ServiceTypeRequest;
//import com.app.f1x.model.Order;
//import com.app.f1x.model.OrderItem;
//import com.app.f1x.model.Product;
//import com.app.f1x.model.ServiceType;
//import com.app.f1x.util.enums.OrderStatus;
//import com.app.f1x.repository.OrderRepository;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/orders")
//@RequiredArgsConstructor
//public class OrderController {
//
//    private final OrderRepository orderRepository;
//
//    @GetMapping
//    public ResponseEntity<List<Order>> getAllOrders() {
//        List<Order> orders = orderRepository.findAll();
//        if (orders.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
//        Optional<Order> order = orderRepository.findById(id);
//        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
//    }
//
//    @PostMapping
//    public ResponseEntity<String> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
//        Order order = new Order();
//        List<OrderItem> orderItems = orderRequest.getOrderItems().stream()
//                .map(orderItemRequest -> {
//                    OrderItem orderItem = new OrderItem();
//                    ServiceTypeRequest serviceTypeRequest = orderItemRequest.getServiceType();
//                    ServiceType serviceType = new ServiceType();
//                    List<Product> requiredProducts = serviceTypeRequest.getRequiredProducts().stream()
//                            .map(productRequest -> {
//                                Product product = new Product();
//                                product.setName(productRequest.getName());
//                                product.setInventoryCost(productRequest.getInventoryCost());
//                                product.setUsageCost(productRequest.getUsageCost());
//                                return product;
//                            }).toList();
//                    List<Product> optionalProducts = serviceTypeRequest.getOptionalProducts().stream()
//                            .map(productRequest -> {
//                                Product product = new Product();
//                                product.setName(productRequest.getName());
//                                product.setInventoryCost(productRequest.getInventoryCost());
//                                product.setUsageCost(productRequest.getUsageCost());
//                                return product;
//                            }).toList();
//                    List<Product> allProducts = new ArrayList<Product>(requiredProducts);
//                    allProducts.addAll(optionalProducts);
//
//                    serviceType.setName(serviceTypeRequest.getName());
//                    serviceType.setDescription(serviceTypeRequest.getDescription());
//                    serviceType.setServiceCost(serviceTypeRequest.getServiceCost());
//                    serviceType.setRequiredProducts(requiredProducts);
//                    serviceType.setOptionalProducts(optionalProducts);
//
//                    orderItem.setOrder(order);
//                    orderItem.setServiceType(serviceType);
//                    orderItem.setProducts(allProducts);
//                    orderItem.setQuantity(orderItemRequest.getQuantity());
//                    orderItem.setSubtotalCost(
//                            (float) allProducts.stream().mapToDouble(Product::getUsageCost).sum() +
//                            serviceTypeRequest.getServiceCost() *
//                            orderItemRequest.getQuantity()
//                    );
//
//                    return orderItem;
//                }).toList();
//
//        order.setCreationTime(LocalDateTime.now());
//        order.setCompletionTime(orderRequest.getCompletionDate());
//        order.setCustomerName(orderRequest.getCustomerName());
//        order.setCustomerContact(orderRequest.getCustomerContact());
//        order.setStatus(OrderStatus.PENDING);
//        order.setNote(orderRequest.getNote());
//        order.setGrandTotal((float) orderItems.stream().mapToDouble(OrderItem::getSubtotalCost).sum());
//        order.setOrderItems(orderItems);
//
//        orderRepository.save(order);
//
//
//
////        Optional<Customer> customerOpt = customerRepository.getById(request.getCustomerId());
////        if (customerOpt.isEmpty()) {
////            return ResponseEntity.badRequest().body("Invalid customer ID");
////        }
////
////        Customer customer = customerOpt.get();
////        Order order = OrderMapper.toOrder(request, customer);
////
////        jdbcClientOrderRepository.create(order);
////        return ResponseEntity.ok("Order created successfully");
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateOrder(@PathVariable int id, @Valid @RequestBody Ordera order) {
//        Optional<Ordera> existingOrder = jdbcClientOrderRepository.getById(id);
//        if (existingOrder.isPresent()) {
//            jdbcClientOrderRepository.update(id, order);
//            return ResponseEntity.ok("Order updated successfully");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteOrder(@PathVariable int id) {
//        Optional<Ordera> existingOrder = jdbcClientOrderRepository.getById(id);
//        if (existingOrder.isPresent()) {
//            jdbcClientOrderRepository.delete(id);
//            return ResponseEntity.ok("Order deleted successfully");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
