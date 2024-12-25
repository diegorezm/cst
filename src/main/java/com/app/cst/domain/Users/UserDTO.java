package com.app.cst.domain.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTO(

          @NotBlank(message = "Username cannot be blank") @Size(min = 4, max = 255, message = "Username must be between 4 and 255 characters") String username,

          @NotBlank(message = "Email cannot be blank") @Email(message = "Invalid email format") String email,

          @NotBlank(message = "Password cannot be blank") @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters") String password) {

}
