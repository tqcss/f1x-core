package com.app.f1x.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "laundry_order")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appUser")
    @Setter
    private AppUser appUser;

    @Setter
    private String customerName;

    @Setter
    private String customerContact;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "laundryOrder", cascade = CascadeType.ALL)
    @Setter
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laundromat")
    @Setter
    private Laundromat laundromat;

    @Setter
    private LocalDateTime orderDateTime;

    @Setter
    private Integer cashierId;

}
