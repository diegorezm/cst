package com.app.cst.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.cst.domain.Users.User;

public interface UserRepository extends JpaRepository<User, Integer> {
     Optional<User> findByEmail(String email);
}
