//package com.app.f1x.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.JoinTable;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//@Entity
//@Table(name = "order_items")
//@Getter
//@NoArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//public class OrderItem {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @EqualsAndHashCode.Include
//    private Long id;
//
//    @Setter
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id", nullable = false)
//    private Order order;
//
//    @Setter
//    private String serviceName;
//
//    @Setter
//    private Float serviceCost;
//
//    @Setter
//    private Integer quantity;
//
//    @Setter
//    private Float subtotalCost;
//
//    @Setter
//    @ManyToMany
//    @JoinTable(
//            name = "required_order_product",
//            joinColumns = @JoinColumn(name = "order_item_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private List<Product> requiredProducts;
//
//    @Setter
//    @ManyToMany
//    @JoinTable(
//            name = "optional_order_product",
//            joinColumns = @JoinColumn(name = "order_item_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private List<Product> optionalProducts;
//
//
//}