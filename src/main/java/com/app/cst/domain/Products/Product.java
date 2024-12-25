package com.app.cst.domain.Products;

import com.app.cst.domain.Model.Model;
import com.app.cst.domain.ProductEntity.ProductEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "products")
@Entity(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product extends Model {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     @ManyToOne
     @JoinColumn(name = "entity_id")
     private ProductEntity productEntity;

     private Boolean sold;

     public Product(ProductEntity productEntity) {
          this.productEntity = productEntity;
          this.sold = false;
     }
}
