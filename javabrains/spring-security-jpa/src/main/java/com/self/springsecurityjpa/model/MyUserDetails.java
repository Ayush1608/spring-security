package com.self.springsecurityjpa.model;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.self.springsecurityjpa.model.entity.MyUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@NoArgsConstructor
public class MyUserDetails implements UserDetails {

  private String userName;
  private String password;
  private boolean isEnabled;
  private List<GrantedAuthority> authorityList;

  public MyUserDetails(MyUser user) {
    this.userName = user.getUsername();
    this.isEnabled = user.isActive();
    this.password = user.getPassword();
    this.authorityList = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }

  @Override public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorityList;
  }

  @Override public String getPassword() {
    return this.password;
  }

  @Override public String getUsername() {
    return this.userName;
  }

  @Override public boolean isAccountNonExpired() {
    return true;
  }

  @Override public boolean isAccountNonLocked() {
    return true;
  }

  @Override public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override public boolean isEnabled() {
    return this.isEnabled;
  }
}
