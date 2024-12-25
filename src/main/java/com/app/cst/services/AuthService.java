package com.app.cst.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.cst.domain.Users.User;
import com.app.cst.domain.Users.UserDTO;
import com.app.cst.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
     private final UserRepository userRepository;

     @Override
     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          return userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("There are no users with this email."));
     }

     public void register(UserDTO payload) {
          var encoder = new BCryptPasswordEncoder();
          String password = encoder.encode(payload.password());
          User user = new User(new UserDTO(payload.username(), payload.email(), password));
          this.userRepository.save(user);
     }
}
