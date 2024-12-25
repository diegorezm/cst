package com.app.cst.components;

import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.app.cst.domain.Users.User;
import com.app.cst.domain.Users.UserDTO;
import com.app.cst.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AddDummyUsers implements CommandLineRunner {

     private final UserRepository userRepository;

     @Override
     public void run(String... args) throws Exception {
          String password = new BCryptPasswordEncoder().encode("123456");
          UserDTO userDTO = new UserDTO("diego", "diego@email.com", password);
          User user = new User(userDTO);
          this.userRepository.save(user);
     }
}
