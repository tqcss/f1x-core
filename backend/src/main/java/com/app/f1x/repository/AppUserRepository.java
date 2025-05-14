package com.app.f1x.repository;

import com.app.f1x.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    public AppUser findAppUserByEmail(String email);

}
