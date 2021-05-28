package com.self.resourceserverwithdsl.security;



import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;


@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

  @Override protected void configure(final HttpSecurity http) throws Exception {
    http.oauth2ResourceServer(c -> c.jwt(j -> j.decoder(jwtDecoder())))
    .authorizeRequests().anyRequest().authenticated();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    byte[] bytes = "fkskkdjjdnsmskndfkncakdnjkbclhnksbcjsbcijbkjfsdf".getBytes();
    SecretKey secretKey = new SecretKeySpec(bytes, 0, bytes.length, "AES");
    return NimbusJwtDecoder.withSecretKey(secretKey).build();
  }
  
}
