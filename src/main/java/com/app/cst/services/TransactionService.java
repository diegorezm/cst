package com.app.cst.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.cst.domain.Exceptions.HttpException;
import com.app.cst.domain.Transactions.Transaction;
import com.app.cst.repositories.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
     private final TransactionRepository transactionRepository;
     private final ProductService productService;

     public Transaction get(Integer id) {
          return this.transactionRepository.findById(id)
                    .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "Could not find this transaction."));
     }

     public List<Transaction> get(LocalDateTime date1, LocalDateTime date2) {
          return this.transactionRepository.findByDateBetween(date1, date2);
     }

     public Page<Transaction> get(Pageable pageable) {
          return this.transactionRepository.findAll(pageable);
     }

     public void create(Integer productId) {
          var product = this.productService.get(productId);
          Transaction transaction = new Transaction(product);
          this.transactionRepository.save(transaction);
     }

     public void delete(Integer id) {
          var transaction = this.get(id);
          this.transactionRepository.delete(transaction);
     }

}
