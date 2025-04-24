package com.app.f1x.repository;

import com.app.f1x.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);
    private final JdbcClient jdbcClient;

    public CustomerRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Customer> getAll() {
        return jdbcClient.sql("SELECT * FROM customer")
                .query(Customer.class)
                .list();
    }

    public Optional<Customer> getById(Long id) {
        return jdbcClient.sql("SELECT * FROM customer WHERE id = :id")
                .param("id", id)
                .query(Customer.class)
                .optional();
    }

    public void create(Customer customer) {
        int affectedRows = jdbcClient.sql("INSERT INTO customer (first_name, last_name, phone_number, email) VALUES (:firstName, :lastName, :phoneNumber, :email)")
                .param("firstName", customer.getFirstName())
                .param("lastName", customer.getLastName())
                .param("phoneNumber", customer.getPhoneNumber())
                .param("email", customer.getEmail())
                .update();

        Assert.state(affectedRows == 1, "Customer creation failed");
    }

    public void update(Long id, Customer customer) {
        int affectedRows = jdbcClient.sql(" UPDATE customer SET first_name = :firstName, last_name = :lastName, phone_number = :phoneNumber, email = :email WHERE id = :id")
                .param("id", id)
                .param("firstName", customer.getFirstName())
                .param("lastName", customer.getLastName())
                .param("phoneNumber", customer.getPhoneNumber())
                .param("email", customer.getEmail())
                .update();

        Assert.state(affectedRows == 1, "Customer update failed");
    }

    public void delete(Long id) {
        int affectedRows = jdbcClient.sql("DELETE FROM customer WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(affectedRows == 1, "Customer delete failed");
    }
}
