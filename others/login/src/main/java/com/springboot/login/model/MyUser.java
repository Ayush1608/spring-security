package com.springboot.login.model;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyUser {

  private String username;
  private String password;
  private boolean isEnabled;
  private List<GrantedAuthority> grantedAuthorities;
}
