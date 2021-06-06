package com.self.springsecurityresourceserver.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Override public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
    resources.tokenStore(tokenStore());
  }

  /**
   * Configuring endpoints security for resource server. In below example, the /demo url will give 403 error as the user saved in Auth server has
   * read authority. Below example stats that even if the user is verified/authorised the url is protected with the given authority and only the
   * user with given authority can access it.
   * @param http
   * @throws Exception
   */
  @Override public void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .mvcMatchers("/demo/**")
        .hasAuthority("write")
        .anyRequest()
        .authenticated();
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(jwtAccessTokenConverter());
  }

  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
    jwtAccessTokenConverter.setSigningKey("fkskkdjjdnsmskndfkncakdnjkbclhnksbcjsbcijbkjfsdf"); // same value as Authorization server.
    return jwtAccessTokenConverter;
  }
}
