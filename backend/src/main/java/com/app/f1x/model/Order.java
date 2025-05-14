package com.app.f1x.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at")
    @Setter
    private LocalDateTime createdAt;

    @Column(name = "customer_name")
    @Setter
    private String customerName;

    @Column(name = "customer_contact")
    @Setter
    private String customerContact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cashier")
    @Setter
    private AppUser cashier;

    @OneToMany(mappedBy = "order")
    @Setter
    private List<OrderItem> orderItems;

    @Column(name = "grand_total_price")
    @Setter
    private Float grandTotalPrice;

}
