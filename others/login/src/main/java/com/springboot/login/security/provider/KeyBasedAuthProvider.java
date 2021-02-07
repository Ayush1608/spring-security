package com.springboot.login.security.provider;


import com.springboot.login.security.authentication.KeyBasedAuthentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@Component
public class KeyBasedAuthProvider implements AuthenticationProvider {

  @Value("${key}")
  String key;

  @Override public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
    String reqKey = authentication.getName();
    if (reqKey.equals(key)) {
      return new KeyBasedAuthentication(null, null, null);
    }
    return null;
  }

  @Override public boolean supports(final Class<?> authentication) {
    return KeyBasedAuthentication.class.equals(authentication);
  }
}
