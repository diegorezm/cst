package com.app.cst.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.cst.domain.Exceptions.HttpException;
import com.app.cst.domain.ProductEntity.ProductEntity;
import com.app.cst.domain.ProductEntity.ProductEntityDTO;
import com.app.cst.repositories.ProductEntityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductEntityService {
     private final ProductEntityRepository productEntityRepository;

     public ProductEntity get(int id) {
          return productEntityRepository.findById(id)
                    .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Product entity not found."));
     }

     public Page<ProductEntity> get() {
          return productEntityRepository.findAll(Pageable.ofSize(10));
     }

     public Page<ProductEntity> get(Pageable pageable) {
          return productEntityRepository.findAll(pageable);
     }

     public Page<ProductEntity> get(String keyword, Pageable pageable) {
          return productEntityRepository.findByNameContainingIgnoreCase(keyword, pageable);
     }

     public void create(ProductEntityDTO dto) {
          ProductEntity productEntity = new ProductEntity(dto);
          this.productEntityRepository.save(productEntity);
     }

     public void update(Integer id, ProductEntityDTO dto) {
          var productEntity = this.get(id);
          productEntity.setName(dto.name());
          productEntity.setPrice(dto.price());
          this.productEntityRepository.save(productEntity);
     }

     public void delete(Integer id) {
          var productEntity = this.get(id);
          this.productEntityRepository.delete(productEntity);
     }
}
