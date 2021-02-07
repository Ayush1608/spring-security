package com.springboot.login.security.provider;


import com.springboot.login.service.MyuserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

  @Autowired
  MyuserDetailsService myuserDetailsService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
    if (authentication.isAuthenticated()) {
      return authentication;
    }
    UserDetails userDetails = myuserDetailsService.loadUserByUsername(authentication.getName());
    if (passwordEncoder.matches(String.valueOf(authentication.getCredentials()), userDetails.getPassword())) {
      return new UsernamePasswordAuthenticationToken(authentication.getName(), userDetails.getPassword(), userDetails.getAuthorities());
    }
    return null;
  }

  @Override public boolean supports(final Class<?> authentication) {

    return UsernamePasswordAuthenticationToken.class.equals(authentication);
  }
}
