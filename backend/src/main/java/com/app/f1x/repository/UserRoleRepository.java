package com.app.f1x.repository;

import com.app.f1x.model.UserRole;
import com.app.f1x.util.enums.EnumUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    Optional<UserRole> findByName(EnumUserRole name);

}
