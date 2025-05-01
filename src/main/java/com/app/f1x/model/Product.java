package com.app.f1x.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laundromat_id")
    @Setter
    private Laundromat laundromat;

    @OneToMany(mappedBy = "position")
    @Setter
    private List<User> users;

    // marked as a required product used in a service
    @ManyToMany(mappedBy = "required_products", fetch = FetchType.LAZY)
    @Setter
    private List<ServiceType> requiredInServices;

    // marked as an optional product used in a service
    @ManyToMany(mappedBy = "optional_product", fetch = FetchType.LAZY)
    @Setter
    private List<ServiceType> optionalInServices;

    // marked as an optional product placed in an OrderItem
    @ManyToMany(mappedBy = "optional_product", fetch = FetchType.LAZY)
    @Setter
    private List<OrderItem> optionalInOrderItem;

    @Column(name = "name", nullable = false)
    @Setter
    private String name;

    @Column(name = "inventory_cost", precision = 10, scale = 2, nullable = false)
    @Setter
    private Float inventoryCost;

    @Column(name = "usage_cost", precision = 10, scale = 2, nullable = false)
    @Setter
    private Float usageCost;

}
