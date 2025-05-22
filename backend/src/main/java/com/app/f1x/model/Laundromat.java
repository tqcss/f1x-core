package com.app.f1x.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "laundromat")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Laundromat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @Setter
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator")
    @Setter
    private AppUser creator;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "laundromat")
    @Setter
    private List<AppUser> users;

    @Column(name = "invite_code")
    @Setter
    private String inviteCode;

    @Column(name = "invite_expiry")
    @Setter
    private LocalDateTime inviteExpiry;

}
