package com.self.resourceserverwithdsl.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

  @Value("${introspection.uri}")
  private String introspectionUri;

  @Override protected void configure(final HttpSecurity http) throws Exception {
    http.oauth2ResourceServer(
      c -> c.opaqueToken(
        c1 -> c1.introspectionUri(introspectionUri)
                .introspectionClientCredentials("resource", "12345")
      )
    )
    .authorizeRequests().anyRequest().authenticated();
  }
}
