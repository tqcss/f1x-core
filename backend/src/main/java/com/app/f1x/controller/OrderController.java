package com.app.f1x.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.app.f1x.model.ServiceType;
import com.app.f1x.repository.ServiceTypeRepository;
import com.app.f1x.payload.request.CreateOrderRequest;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/app")
public class OrderController {

    private final ServiceTypeRepository serviceTypeRepository;

    @Autowired
    public OrderController(ServiceTypeRepository serviceTypeRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
    }

    @GetMapping({"/orders", "/orders/new"})
    public String showOrderForm(Model model) {
        List<ServiceType> serviceTypes = serviceTypeRepository.findAll();
        model.addAttribute("serviceTypes", serviceTypes);
        model.addAttribute("orderRequest", new CreateOrderRequest());
        return "orders";
    }

    @PostMapping("/orders")
    public String createOrder(@ModelAttribute("orderRequest") CreateOrderRequest orderRequest, Model model) {
        System.out.println("Order Details:");
        System.out.println("Customer Name: " + orderRequest.getCustomerName());
        System.out.println("Customer Contact: " + orderRequest.getCustomerContact());

        if (orderRequest.getOrderItems() != null) {
            for (int i = 0; i < orderRequest.getOrderItems().size(); i++) {
                System.out.println("Item " + (i + 1) + ":");
                System.out.println("  Service Name: " + orderRequest.getOrderItems().get(i).getServiceName());
                System.out.println("  Quantity: " + orderRequest.getOrderItems().get(i).getQuantity());
            }
        }

        return "redirect:/orders/success";
    }

    @GetMapping("/orders/success")
    public String showOrderSuccess() {
        return "orderSuccess";
    }
}