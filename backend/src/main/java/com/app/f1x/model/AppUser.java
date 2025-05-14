package com.app.f1x.model;

import com.app.f1x.util.enums.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "app_user")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "created_at", nullable = false)
    @Setter
    private LocalDateTime createdAt;

//    @OneToOne(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Setter
//    private Laundromat createdLaundromat;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "laundromat_id")
//    @Setter
//    private Laundromat laundromat;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "laundromat_role_id")
//    @Setter
//    private LaundromatRole laundromatRole;

    @OneToMany(mappedBy = "cashier")
    private List<Order> ordersMade;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    @Setter
    private UserRole userRole;

    @Column(name = "email", unique = true, nullable = false)
    @Setter
    private String email;

    @Column(name = "first_name", nullable = false)
    @Setter
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Setter
    private String lastName;

    @Column(name = "password", nullable = false)
    @Setter
    private String password;

}