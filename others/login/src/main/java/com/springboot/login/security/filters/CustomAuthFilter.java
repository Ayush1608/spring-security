package com.springboot.login.security.filters;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.springboot.login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;


@Component
public class CustomAuthFilter implements Filter {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  LoginRepository loginRepository;

  @Override
  public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                       final FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
    HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

//    String auth = httpServletRequest.getHeader("Authorization");
//
//    if(auth != null) {
//      try {
////        Authentication authentication = authenticationManager.authenticate(new KeyBasedAuthentication(auth, null));
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth, null));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//      }
//      catch (Exception e) {
//        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
//      }
//    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);

  }
}
