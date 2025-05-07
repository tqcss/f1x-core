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
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

    // marked as a required product used in a service
    @ManyToMany(mappedBy = "requiredProducts", fetch = FetchType.LAZY)
    @Setter
    private List<ServiceType> requiredInServices;

    // marked as an optional product used in a service
    @ManyToMany(mappedBy = "optionalProducts", fetch = FetchType.LAZY)
    @Setter
    private List<ServiceType> optionalInServices;

    // marked as an optional product placed in an OrderItem
    @ManyToMany(mappedBy = "optionalProducts", fetch = FetchType.LAZY)
    @Setter
    private List<OrderItem> optionalInOrderItem;

    @Column(name = "created_at", nullable = false)
    @Setter
    private LocalDateTime createdAt;

    @Column(name = "name", nullable = false)
    @Setter
    private String name;

    @Column(name = "inventory_cost", nullable = false)
    @Setter
    private Float inventoryCost;

    @Column(name = "usage_cost", nullable = false)
    @Setter
    private Float usageCost;

}
