package com.self.springsecurityresourceserver.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;


@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Autowired
  DataSource dataSource;

  @Override public void configure(final ResourceServerSecurityConfigurer resources) throws Exception {
    resources.tokenStore(getTokenStore());
  }

  @Bean
  public TokenStore getTokenStore() {
    return new JdbcTokenStore(dataSource);
  }


}
