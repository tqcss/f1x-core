package com.app.f1x.model;

import com.app.f1x.util.enums.EnumUserRole;
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
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @OneToOne(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter
    private Laundromat createdLaundromat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laundromat_id")
    @Setter
    private Laundromat laundromat;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "laundromat_role_id")
    @Setter
    private LaundromatRole laundromatRole;

    @Column(name = "created_at", nullable = false)
    @Setter
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    @Setter
    private EnumUserRole userRole;

    @Column(name = "email", nullable = false)
    @Setter
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    @Setter
    private String username;

    @Column(name = "first_name", nullable = false)
    @Setter
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Setter
    private String lastName;

    @Column(name = "password", nullable = false)
    @Setter
    private String password;

    @Column(name = "locked", nullable = false)
    @Setter
    private Boolean locked;

    @Column(name = "enabled", nullable = false)
    @Setter
    private Boolean enabled;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.toString());
//        return Collections.singletonList(authority);
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return !this.locked;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return this.enabled;
//    }

}
