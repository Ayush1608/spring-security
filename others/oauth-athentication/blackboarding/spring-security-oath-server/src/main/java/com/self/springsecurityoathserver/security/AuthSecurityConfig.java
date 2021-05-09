package com.self.springsecurityoathserver.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;


@EnableAuthorizationServer
@Configuration
public class AuthSecurityConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  DataSource dataSource;


  @Override public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
    /*
      In Password Grant type, the user sends its creds to client which then sends it to Auth. server. See URL, we pass username password in params.
       It is deprecated.
     */
    clients.inMemory()
           /*
                Refresh token grant type is always used with another grant type. It helps us to generate another token without storing the user's
                creds and making him authenticate again. A refresh token with authorization token is generated. this token helps to generate
                another authorization token if required.
            */
           .withClient("client1")
           .secret("secret1")
           .scopes("read")
           .authorizedGrantTypes("password", "refresh_token"); //localhost:8080/oauth/token?grant_type=password&username=user&password=1234&scope=read
  }

  @Override public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager)
             .tokenStore(getTokenStore());
  }

  @Bean
  public TokenStore getTokenStore() {
    return new JdbcTokenStore(dataSource);
  }
}
