package com.app.f1x.controller;

import com.app.f1x.model.AppUser;
import com.app.f1x.model.Order;
import com.app.f1x.model.OrderItem;
import com.app.f1x.model.ServiceProduct;
import com.app.f1x.repository.AppUserRepository;
import com.app.f1x.repository.OrderItemRepository;
import com.app.f1x.repository.OrderRepository;
import com.app.f1x.repository.ServiceProductRepository;
import com.app.f1x.service.AppUserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/app")
public class OrderController {

    @PersistenceContext
    private EntityManager entityManager;

    private final AppUserService appUserService;
    private final ServiceProductRepository serviceProductRepository;
    private final OrderItemRepository orderItemRepository;
    private final AppUserRepository appUserRepository;
    private final OrderRepository orderRepository;

    public OrderController(AppUserService appUserService, ServiceProductRepository serviceProductRepository, OrderItemRepository orderItemRepository, AppUserRepository appUserRepository, OrderRepository orderRepository) {
        this.appUserService = appUserService;
        this.serviceProductRepository = serviceProductRepository;
        this.orderItemRepository = orderItemRepository;
        this.appUserRepository = appUserRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    @PostMapping("/currentOrder/addItem")
    public String addCurrentOrderItem(@RequestParam Integer serviceId, Authentication authentication) {
        AppUser appUser = appUserService.findAppUserByEmail(authentication.getName()).orElseThrow();
        Order currentOrder;

        if (appUser.getCurrentOrder() == null) {
            currentOrder = new Order();
            currentOrder.setAppUser(appUser);
            currentOrder.setOrderItems(new ArrayList<>());

            orderRepository.save(currentOrder);
            entityManager.flush();

            appUser.setCurrentOrder(currentOrder);
            appUserRepository.save(appUser);
        } else {
            currentOrder = appUser.getCurrentOrder();
        }

        if (currentOrder.getOrderItems() == null) {
            currentOrder.setOrderItems(new ArrayList<>());
        }

        List<OrderItem> orderItems = currentOrder.getOrderItems();
        OrderItem existing = null;

        for (OrderItem item : orderItems) {
            if (item.getServiceProduct().getId().equals(serviceId)) {
                existing = item;
                break;
            }
        }

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + 1);
        } else {
            ServiceProduct product = serviceProductRepository.getReferenceById(serviceId);
            existing = OrderItem.builder()
                    .laundryOrder(currentOrder)
                    .serviceProduct(product)
                    .quantity(1)
                    .build();

            orderItems.add(existing);
        }

        orderItemRepository.save(existing);

        return "redirect:/app/home";
    }

    @PostMapping("/currentOrder/cancel")
    public String cancelOrder(@RequestParam Integer currentOrderId) {
        Order currentOrder = orderRepository.getReferenceById(currentOrderId);
        orderRepository.delete(currentOrder);

        return "redirect:/app/home";
    }

}
