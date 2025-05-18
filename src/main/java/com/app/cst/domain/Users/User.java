package com.app.cst.domain.Users;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.cst.domain.Model.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "cst_users")
@Entity(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User extends Model implements UserDetails {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     private String username;
     private String email;
     private String password;

     public User(UserDTO dto) {
          this.username = dto.username();
          this.email = dto.email();
          this.password = dto.password();
     }

     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
          return List.of(new SimpleGrantedAuthority("ROLE_USER"));
     }

     public UserSafe toUserSafe() {
          return new UserSafe(username, email, this.createdAt, updatedAt);
     }

}
