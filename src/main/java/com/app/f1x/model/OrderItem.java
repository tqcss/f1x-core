package com.app.f1x.model;

import com.app.f1x.model.enums.ServiceType;
import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ServiceType serviceType;
    private Integer quantity;
    private Float unitPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem() {}

    public OrderItem(Order order, ServiceType serviceType, Integer quantity) {
        this.serviceType = serviceType;
        this.quantity = quantity;
        this.order = order;

        // to be implemented
        // this.unitPrice = OrderService.calculateUnitPrice(serviceType, quantity);
    }

    public Long getId() { return this.id; }

    public ServiceType getServiceType() { return this.serviceType; }
    public Integer getQuantity() { return this.quantity; }
    public Float getUnitPrice() { return this.unitPrice; }

    public Order getOrder() {return this.order; }
}
