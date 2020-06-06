package com.self.csrfinspringboot.security.filter;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.self.csrfinspringboot.model.CustomCSRFToken;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;


@Component
public class CustomCSRFFIlter implements Filter {

  @Override
  public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse,
                       final FilterChain filterChain) throws IOException, ServletException {

    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;

    CsrfToken csrfToken = (CustomCSRFToken) request.getAttribute("csrf");
    System.out.println(csrfToken.getToken());
    filterChain.doFilter(request, response);
  }
}
