package com.self.springsecurityoath.security;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override protected void configure(final HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().oauth2Login();
  }


  /* The below implementation is used in place of providing properties: (Either comment code below or the properties in application.properties)
        spring.security.oauth2.client.registration.google.clientId=#############
        spring.security.oauth2.client.registration.google.clientSecret=############

     We can provide our custom implementation using below.
   */

  /**
   * ClientRegistrationRepository is an interface which has method findByRegistrationId() which can be overriden to provide an implementation
   * for getting client details from the database like we do for users in spring security flow and clients in auth server flow.
   * @return
   */
  @Bean
  public ClientRegistrationRepository clientRegistrationRepository() {
    return new InMemoryClientRegistrationRepository(clientRegistration());
  }
  /**
   * CommonOAuth2Provider already has all the default values needed for various auth servers. eg- Fb, Github, Google etc
   * @return
   */
  private ClientRegistration clientRegistration() {
    return CommonOAuth2Provider.FACEBOOK.getBuilder("facebook") //here facebook is registration id.
      .clientId("######")
      .clientSecret("######")
      .build();
  }
}
