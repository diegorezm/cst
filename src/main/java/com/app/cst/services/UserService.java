package com.app.cst.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.cst.domain.Exceptions.HttpException;
import com.app.cst.domain.Users.User;
import com.app.cst.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
     private final UserRepository userRepository;

     public User get(Integer id) {
          return this.userRepository.findById(id)
                    .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, "User not found."));
     }

     public void update(Integer id, String name) {
          var user = this.get(id);
          user.setUsername(name);
          this.userRepository.save(user);
     }
}
