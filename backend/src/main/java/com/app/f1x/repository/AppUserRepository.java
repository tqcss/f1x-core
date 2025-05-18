package com.app.f1x.repository;

import com.app.f1x.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    public Optional<AppUser> findAppUserByEmail(String email);

}
