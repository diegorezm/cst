package com.app.cst.services;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.cst.domain.Exceptions.HttpException;
import com.app.cst.domain.Products.Product;
import com.app.cst.domain.Transactions.Transaction;
import com.app.cst.repositories.ProductRepository;
import com.app.cst.repositories.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
     private final ProductRepository productRepository;
     private final ProductEntityService productEntityService;
     private final TransactionRepository transactionRepository;

     public Product get(Integer id) throws HttpException {
          return this.productRepository.findById(id)
                    .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "This product was not found."));
     }

     public void create(Integer productEntityId) {
          var productEntity = this.productEntityService.get(productEntityId);
          Product product = new Product(productEntity);
          this.productRepository.save(product);

     }

     public void delete(Integer id) {
          var product = this.get(id);
          this.productRepository.delete(product);
     }

     public void update(Integer id, Boolean sold) {
          var product = this.get(id);
          product.setSold(sold);
          this.productRepository.save(product);
          if (sold) {
               Transaction transaction = new Transaction(product);
               this.transactionRepository.save(transaction);
          } else {
               Transaction transaction = this.transactionRepository.findByProductId(product.getId()).orElse(null);
               if (transaction != null)
                    this.transactionRepository.delete(transaction);

          }
     }

     public void update(Integer id, Boolean sold, LocalDateTime date) {
          var product = this.get(id);
          product.setSold(sold);
          this.productRepository.save(product);
          if (sold) {
               Transaction transaction = new Transaction(product, date);
               this.transactionRepository.save(transaction);
          } else {
               Transaction transaction = this.transactionRepository.findByProductId(product.getId()).orElse(null);
               if (transaction != null)
                    this.transactionRepository.delete(transaction);

          }
     }
}
