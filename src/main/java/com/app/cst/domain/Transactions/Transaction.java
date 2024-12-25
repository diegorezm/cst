package com.app.cst.domain.Transactions;

import java.time.LocalDateTime;

import com.app.cst.domain.Products.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "product_transactions")
@Entity(name = "product_transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     @OneToOne
     @JoinColumn(name = "product_id")
     private Product product;

     private LocalDateTime date;

     public Transaction(Product product) {
          this.product = product;
          this.date = LocalDateTime.now();
     }

     public Transaction(Product product, LocalDateTime date) {
          this.product = product;
          this.date = date;
     }
}
