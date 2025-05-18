package com.app.cst.components;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;

import com.app.cst.domain.ProductEntity.ProductEntity;
import com.app.cst.domain.ProductEntity.ProductEntityDTO;
import com.app.cst.domain.Products.Product;
import com.app.cst.repositories.ProductEntityRepository;
import com.app.cst.repositories.ProductRepository;
import com.app.cst.services.ProductService;
import com.github.javafaker.Faker;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddDummyProducts implements CommandLineRunner {
     private final ProductEntityRepository productEntityRepository;
     private final ProductRepository productRepository;

     private final ProductService productService;

     @Override
     public void run(String... args) throws Exception {
          List<ProductEntity> productEntities = new ArrayList<>();
          Faker faker = new Faker();
          for (int i = 0; i < 100; i++) {
               ProductEntityDTO productEntityDTO = new ProductEntityDTO(faker.commerce().productName(),
                         faker.number().numberBetween(2, 500));
               ProductEntity productEntity = new ProductEntity(productEntityDTO);
               productEntities.add(productEntity);
          }

          this.productEntityRepository.saveAll(productEntities);
          int currentYear = java.time.Year.now().getValue();

          // Create a date range (from 2021 to current year)
          Date start = java.sql.Date.valueOf("2021-01-01");
          Date end = java.sql.Date.valueOf(currentYear + "-12-31");

          for (ProductEntity entity : productEntities) {
               int amountOfProducts = faker.number().numberBetween(1, 10);
               for (int i = 0; i < amountOfProducts; i++) {
                    Product product = new Product(entity);
                    this.productRepository.save(product);
                    if (faker.random().nextBoolean()) {
                         Date randomDate = faker.date().between(start, end);
                         LocalDateTime localDateTime = randomDate.toInstant()
                                   .atZone(ZoneId.systemDefault())
                                   .toLocalDateTime();

                         this.productService.update(product.getId(), true, localDateTime);
                    }
               }

          }
          ;
     }

}
