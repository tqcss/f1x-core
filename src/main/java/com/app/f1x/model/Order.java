package com.app.f1x.model;

import com.app.f1x.model.enums.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;

    private Float amount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    public Order() {}

    public Order(Customer customer, LocalDateTime dueDate, List<OrderItem> items) {
        LocalDateTime now = LocalDateTime.now();

        if (dueDate.isBefore(now)) {
            throw new IllegalArgumentException("Due date cannot be before the date today");
        }

        this.status = OrderStatus.PENDING;
        this.createdAt = now;
        this.updatedAt = now;

        this.customer = customer;
        this.dueDate = dueDate;
        this.items = items;

        // to be implemented
        // this.amount = OrderService.calculateCost()
    }

    private void updateDate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return this.id; }

    public LocalDateTime getCreatedAt() { return this.createdAt; }
    public LocalDateTime getUpdatedAt() { return this.updatedAt; }
    public LocalDateTime getDueDate() { return this.dueDate; }

    public Float getAmount() { return this.amount; }
    public OrderStatus getStatus() { return this.status; }
    public Customer getCustomer() { return this.customer; }
    public List<OrderItem> getItems() { return this.items; }

    public void setDueDate(LocalDateTime dueDate) { updateDate(); this.dueDate = dueDate; }
    public void setAmount(Float amount) { this.amount = amount; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public void setCustomer(Customer customer) { updateDate(); this.customer = customer; }

    public void addItem(OrderItem item) { updateDate(); items.add(item); }
    public void removeItem(Long itemId) { updateDate(); items.removeIf(item -> item.getId().equals(itemId)); }

}
