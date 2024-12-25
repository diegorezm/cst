package com.app.cst.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.cst.domain.Transactions.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
     Optional<Transaction> findByProductId(Integer productId);

     List<Transaction> findByDateBetween(LocalDateTime date1, LocalDateTime date2);
}
