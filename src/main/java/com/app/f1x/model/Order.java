//package com.app.f1x.model;
//
//import com.app.f1x.util.enums.OrderStatus;
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.Table;
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Table(name = "orders")
//@Getter
//@NoArgsConstructor
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//public class Order {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @EqualsAndHashCode.Include
//    private Long id;
//
//    @Setter
//    private LocalDateTime creationTime;
//
//    @Setter
//    private LocalDateTime completionTime;
//
//    @Setter
//    private String customerName;
//
//    @Setter
//    private String customerContact;
//
//    @Setter
//    @Enumerated(EnumType.STRING)
//    private OrderStatus status;
//
//    @Setter
//    private String note;
//
//    @Setter
//    private Float grandTotal;
//
//    @Setter
//    @OneToMany(mappedBy = "order_items", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<OrderItem> orderItems;
//
//}
