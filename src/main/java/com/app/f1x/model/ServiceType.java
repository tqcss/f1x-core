package com.app.f1x.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "service_types")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ServiceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Setter
    private String name;

    @Setter
    private String description;

    @Setter
    private Float serviceCost;

    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "service_product_required",
            joinColumns = @JoinColumn(name = "service_type_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> requiredProducts;

    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "service_product_optional",
            joinColumns = @JoinColumn(name = "service_type_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> optionalProducts;

}
