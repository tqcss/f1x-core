package com.app.f1x.repository;

import com.app.f1x.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

    private static final Logger logger = LoggerFactory.getLogger(OrderRepository.class);
    private final JdbcClient jdbcClient;

    public OrderRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Order> getAll() {
        return jdbcClient.sql("SELECT * FROM order")
                .query(Order.class)
                .list();
    }

    public Optional<Order> getById(int id) {
        return  jdbcClient.sql("SELECT * FROM order WHERE id = :id")
                .param("id", id)
                .query(Order.class)
                .optional();
    }

    public void create(Order order) {
        int affectedRows = jdbcClient.sql("INSERT INTO order (created_at, updated_at, due_date, amount, status, customer_id) VALUES (:createdAt, :updatedAt, :dueDate, :amount, :status, :customerId)")
                .param("createdAt", order.getCreatedAt())
                .param("updatedAt", order.getUpdatedAt())
                .param("dueDate", order.getDueDate())
                .param("amount", order.getAmount())
                .param("status", order.getStatus().toString())
                .param("customerId", order.getCustomer().getId())
                .update();

        Assert.state(affectedRows == 1, "Order creation failed");
    }

    public void bulkCreate(List<Order> orders) {
        orders.forEach(this::create);
    }

    public void update(int id, Order order) {
        int affected_rows = jdbcClient.sql("UPDATE order SET updated_at = :updatedAt, due_date = :dueDate, amount = :amount, status = :status, customer_id = :customerId WHERE id = :id")
                .param("updatedAt", LocalDateTime.now()) // Update updatedAt to now
                .param("dueDate", order.getDueDate())
                .param("amount", order.getAmount())
                .param("status", order.getStatus().toString())
                .param("customerId", order.getCustomer().getId())
                .param("id", id)
                .update();

        Assert.state(affected_rows == 1, "Order update failed");
    }

    public void delete(int id) {
        int affectedRows = jdbcClient.sql("DELETE FROM `order` WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(affectedRows == 1, "Order delete failed");
    }

    public int count() {
        return jdbcClient.sql("SELECT COUNT(*) FROM workout")
                .query(Integer.class)
                .single();
    }

}
