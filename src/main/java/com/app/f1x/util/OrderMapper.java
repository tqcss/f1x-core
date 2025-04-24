package com.app.f1x.util;

import com.app.f1x.dto.CreateOrderRequest;
import com.app.f1x.model.Customer;
import com.app.f1x.model.Order;
import com.app.f1x.model.enums.OrderStatus;

public class OrderMapper {

    public Order toOrder(CreateOrderRequest request, Customer customer) {
        Order order = new Order();
        order.setDueDate(request.getDueDate());
        order.setAmount(request.getAmount());
        order.setCustomer(customer);
        order.setStatus(OrderStatus.PENDING);
        return order;
    }

}
