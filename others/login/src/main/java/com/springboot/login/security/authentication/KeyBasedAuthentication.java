package com.springboot.login.security.authentication;


import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


public class KeyBasedAuthentication extends UsernamePasswordAuthenticationToken {

  public KeyBasedAuthentication(final Object principal, final Object credentials) {
    super(principal, credentials);
  }

  public KeyBasedAuthentication(final Object principal, final Object credentials,
                                final Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
  }


//  @Override public Collection<? extends GrantedAuthority> getAuthorities() {
//    return null;
//  }
//
//  @Override public Object getCredentials() {
//    return null;
//  }
//
//  @Override public Object getDetails() {
//    return null;
//  }
//
//  @Override public Object getPrincipal() {
//    return null;
//  }
//
//  @Override public boolean isAuthenticated() {
//    return false;
//  }
//
//  @Override public void setAuthenticated(final boolean b) throws IllegalArgumentException {
//
//  }
//
//  @Override public String getName() {
//    return null;
//  }
}
