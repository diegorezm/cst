package com.app.cst.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.cst.domain.Products.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
