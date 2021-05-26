package com.self.oauthserver.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;


@EnableAuthorizationServer
@Configuration
public class AuthSecurityConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @Override public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager)
             .userDetailsService(userDetailsService)
             .tokenStore(tokenStore())
             .accessTokenConverter(jwtAccessTokenConverter());
  }

  @Override public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
           .withClient("client1")
           .secret("secret1")
           .authorizedGrantTypes("password")
           .scopes("read")
           .and()
           .withClient("resource")
           .secret("rssecret");
  }

  @Override public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception {
    /*
     * Used to expose public key to whoever calls this API
     * params -> isAuthenticated, permitAll()
     * URL -> /oauth/token_key
    */
    security.tokenKeyAccess("isAuthenticated()");
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(jwtAccessTokenConverter());
  }

  @Bean
  public JwtAccessTokenConverter jwtAccessTokenConverter() {
    JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
    /*
     * Run below command in terminal to generate a key-pair
     * keytool -genkeypair -alias sskey -keyalg RSA -keypass sskey123 -keystore sskey.jks -storepass sskey123
     * keytool -> tool directly available with JDK
     * genkeypair -> composed of public and the private keys
     * alias -> just a name to give to our key
     * keyalg -> algorith to create the key
     * keypass -> password for our private key
     * keystore -> filename where the key will be generated (in this project putting in resources for easy access)
     * storepass -> password for the file itself
     *
     * Then run below to extract public key from private key generated
     * keytool -list -rfc --keystore sskey.jks | openssl x509 -inform pem -pubkey
     *
     * Then copy value of public key which has to be used in resource server.
     */
    KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("sskey.jks"), "sskey123".toCharArray());
    jwtAccessTokenConverter.setKeyPair(keyStoreKeyFactory.getKeyPair("sskey"));

    return jwtAccessTokenConverter;
  }
}
