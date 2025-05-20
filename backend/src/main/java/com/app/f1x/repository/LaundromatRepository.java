package com.app.f1x.repository;

import com.app.f1x.model.Laundromat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaundromatRepository extends JpaRepository<Laundromat, Integer> {



}
