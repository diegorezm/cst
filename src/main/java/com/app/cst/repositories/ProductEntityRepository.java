package com.app.cst.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.cst.domain.ProductEntity.ProductEntity;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Integer> {
     Page<ProductEntity> findByNameContainingIgnoreCase(String keyword, Pageable paging);
}
