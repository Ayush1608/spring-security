package com.self.resourceserverwithdsl.security;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.stream.Collectors;

import com.nimbusds.jose.shaded.json.JSONArray;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;


@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

  /**
   * Configuring endpoints security for resource server. In below example, the /demo url will give 403 error as the user saved in Auth server has
   * read authority. Below example stats that even if the user is verified/authorised the url is protected with the given authority and only the
   * user with given authority can access it.
   * @param http
   * @throws Exception
   */
  @Override protected void configure(final HttpSecurity http) throws Exception {
    http.oauth2ResourceServer(
      c -> c.jwt(
        j -> j.decoder(jwtDecoder())
              // Converter is necessary to extract authorities from token here. Authorities are not extracted by default in DSL method.
              .jwtAuthenticationConverter(converter())
      ))
        .authorizeRequests()
        .mvcMatchers("/demo/**")
        .hasAuthority("write")
        .anyRequest()
        .authenticated();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    byte[] bytes = "fkskkdjjdnsmskndfkncakdnjkbclhnksbcjsbcijbkjfsdf".getBytes();
    SecretKey secretKey = new SecretKeySpec(bytes, 0, bytes.length, "AES");
    return NimbusJwtDecoder.withSecretKey(secretKey).build();
  }

  @Bean
  public JwtAuthenticationConverter converter() {
    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
      JSONArray a = (JSONArray) jwt.getClaims().get("authorities");
      return a.stream()
              .map(String::valueOf)
              .map(SimpleGrantedAuthority::new)
              .collect(Collectors.toList());
    });
    return jwtAuthenticationConverter;
  }

}
