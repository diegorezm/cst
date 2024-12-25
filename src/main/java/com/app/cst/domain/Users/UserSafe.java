package com.app.cst.domain.Users;

import java.time.LocalDateTime;

public record UserSafe(
          String username,
          String email,
          LocalDateTime createdAt,
          LocalDateTime updatedAt) {

}
