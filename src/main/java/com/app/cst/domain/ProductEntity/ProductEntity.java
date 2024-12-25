package com.app.cst.domain.ProductEntity;

import java.util.List;

import com.app.cst.domain.Model.Model;
import com.app.cst.domain.Products.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "product_entities")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity extends Model {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     private String name;
     private Integer price;

     @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Product> products;

     public ProductEntity(ProductEntityDTO dto) {
          this.name = dto.name();
          this.price = dto.price();
     }
}
