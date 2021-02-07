package com.self.csrfinspringboot.security.csrf;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.self.csrfinspringboot.model.CustomCSRFToken;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;


public class CustomCSRFRepository implements CsrfTokenRepository {

  @Override public CsrfToken generateToken(final HttpServletRequest httpServletRequest) {
    return new CustomCSRFToken("ayush", "csrf", "token");
  }

  @Override
  public void saveToken(final CsrfToken csrfToken, final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) {

  }

  @Override public CsrfToken loadToken(final HttpServletRequest httpServletRequest) {
    return null;
  }
}
