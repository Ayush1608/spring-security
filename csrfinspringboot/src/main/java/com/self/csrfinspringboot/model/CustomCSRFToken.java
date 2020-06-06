package com.self.csrfinspringboot.model;


import lombok.AllArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;

@AllArgsConstructor
public class CustomCSRFToken implements CsrfToken {

  private String header;
  private String parameter;
  private String token;

  @Override public String getHeaderName() { return this.header; }

  @Override public String getParameterName() {
    return this.parameter;
  }

  @Override public String getToken() {
    return this.token;
  }
}
