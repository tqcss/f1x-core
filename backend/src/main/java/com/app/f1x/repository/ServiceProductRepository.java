package com.app.f1x.repository;

import com.app.f1x.model.ServiceProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProductRepository extends JpaRepository<ServiceProduct, Integer> {
}
