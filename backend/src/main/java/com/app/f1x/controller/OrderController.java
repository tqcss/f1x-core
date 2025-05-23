package com.app.f1x.controller;

import com.app.f1x.model.AppUser;
import com.app.f1x.model.Order;
import com.app.f1x.model.OrderItem;
import com.app.f1x.model.ServiceProduct;
import com.app.f1x.payload.response.LaundromatDetailsResponse;
import com.app.f1x.repository.AppUserRepository;
import com.app.f1x.repository.OrderItemRepository;
import com.app.f1x.repository.OrderRepository;
import com.app.f1x.repository.ServiceProductRepository;
import com.app.f1x.service.AppUserService;
import com.app.f1x.service.LaundromatService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/app")
public class OrderController {

    private final LaundromatService laundromatService;
    @PersistenceContext
    private EntityManager entityManager;

    private final AppUserService appUserService;
    private final ServiceProductRepository serviceProductRepository;
    private final OrderItemRepository orderItemRepository;
    private final AppUserRepository appUserRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(AppUserService appUserService, ServiceProductRepository serviceProductRepository, OrderItemRepository orderItemRepository, AppUserRepository appUserRepository, OrderRepository orderRepository, LaundromatService laundromatService) {
        this.appUserService = appUserService;
        this.serviceProductRepository = serviceProductRepository;
        this.orderItemRepository = orderItemRepository;
        this.appUserRepository = appUserRepository;
        this.orderRepository = orderRepository;
        this.laundromatService = laundromatService;
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

//    @Transactional
//    @PostMapping("/currentOrder/place")
//    public String placeOrder(@RequestParam(defaultValue = "anonymous") String customerName, @RequestParam(required = false, defaultValue = "") String customerContact, @RequestParam Integer orderId, Authentication authentication) {
//        Order currentOrder = orderRepository.findById(orderId)
//                .orElseThrow(() -> new RuntimeException("Order not found"));
//
//        AppUser appUser = currentOrder.getAppUser();
//
//        currentOrder.setCustomerName(customerName);
//        currentOrder.setCustomerContact(customerContact);
//        currentOrder.setOrderDateTime(LocalDateTime.now());
//        currentOrder.setCashierId(appUser.getId());
//        currentOrder.setLaundromat(appUser.getLaundromat());
//
//        orderRepository.save(currentOrder);
//
//        appUser.setCurrentOrder(null);
//        appUserRepository.save(appUser);
//
//        Order newOrder = new Order();
//        newOrder.setAppUser(appUser);
//        newOrder.setLaundromat(appUser.getLaundromat());
//        orderRepository.save(newOrder);
//
//        appUser.setCurrentOrder(newOrder);
//        appUserRepository.save(appUser);
//
//        return "redirect:/app/home";
//    }

    @Transactional
    @PostMapping("/currentOrder/place")
    public String placeOrder(@RequestParam(defaultValue = "anonymous") String customerName, @RequestParam(required = false, defaultValue = "") String customerContact, @RequestParam Integer orderId, Authentication authentication) {
        var currentOrder = orderRepository.getReferenceById(orderId);
        var appUser = appUserService.findAppUserByEmail(authentication.getName()).get();

        currentOrder.setCustomerName(customerName);
        currentOrder.setCustomerContact(customerContact);
        currentOrder.setOrderDateTime(LocalDateTime.now());
        currentOrder.setCashierId(appUser.getId());
        currentOrder.setLaundromat(appUser.getLaundromat());
        currentOrder.setAppUser(null);

        orderRepository.save(currentOrder);

        appUser.setCurrentOrder(null);
        appUserRepository.save(appUser);

        return "redirect:/app/home";
    }

    @GetMapping("/orders")
    public String orders(Authentication authentication, Model model) {
        LaundromatDetailsResponse laundromatDetailsResponse = laundromatService.getLaundromatDetails(authentication.getName());
        List<Order> orders = orderRepository.findAllByLaundromat_Id(appUserRepository.findAppUserByEmail(authentication.getName()).get().getLaundromat().getId());

        model.addAttribute("orders", orders.reversed());
        model.addAttribute("laundromatDetails", laundromatDetailsResponse);

        return "orders";
    }

}
