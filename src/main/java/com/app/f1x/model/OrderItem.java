package com.app.f1x.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "orderItem")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "optional_order_item_product",
            joinColumns = @JoinColumn(name = "order_item_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> optionalProducts;

    @Column(name = "serviceName", nullable = false)
    private String serviceName;

    @Column(name = "serviceCost", precision = 10, scale = 2, nullable = false)
    private Float serviceCost;

    @Column(name = "quantity", precision = 8, nullable = false)
    private Integer quantity;

    @Column(name = "subtotal", precision = 10, scale = 2, nullable = false)
    private Float subtotal;

}