package com.app.cst.domain.Users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateNameDTO(
          @NotBlank(message = "Username cannot be blank") @Size(min = 4, max = 255, message = "Username must be between 4 and 255 characters") String username) {

}
