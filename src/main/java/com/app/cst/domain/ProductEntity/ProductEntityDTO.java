package com.app.cst.domain.ProductEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ProductEntityDTO(
          @NotBlank(message = "Name cannot be blank") @Size(min = 2, max = 255, message = "Name has to be between 2 and 255 characters") String name,

          @PositiveOrZero(message = "Price cannot be negative") Integer price) {
}
