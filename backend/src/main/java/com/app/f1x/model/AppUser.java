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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "app_user")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at")
    @Setter
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    @Setter
    private UserRole userRole;

    @Column(name = "first_name")
    @Setter
    private String firstName;

    @Column(name = "last_name")
    @Setter
    private String lastName;

    @Column(name = "email")
    @Setter
    private String email;

    @Column(name = "password")
    @Setter
    private String password;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "creator")
    @Setter
    private Laundromat createdLaundromat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laundromat")
    @Setter
    private Laundromat laundromat;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "appUser")
    @Setter
    private Order currentOrder;

}
