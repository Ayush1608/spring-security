package com.self.springsecurityjwt.filters;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.self.springsecurityjwt.service.MyUserDetailsService;
import com.self.springsecurityjwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtFilter extends OncePerRequestFilter {

  @Autowired
  MyUserDetailsService myUserDetailsService;

  @Autowired
  JwtUtil jwtUtil;

  @Override protected void doFilterInternal(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse,
                                            final FilterChain filterChain) throws ServletException, IOException {
    final String authHeader = httpServletRequest.getHeader("Authorization");
    String userName = null;
    String jwt = null;

    if(authHeader != null && authHeader.startsWith("Bearer ")) {
      jwt = authHeader.substring(7);
      userName = jwtUtil.extractUsername(jwt);
    }

    if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = myUserDetailsService.loadUserByUsername(userName);
      final boolean isVerifiedToken = jwtUtil.validateToken(jwt, userDetails);
      if(isVerifiedToken) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
          userDetails, null, new ArrayList<>()
        );
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
