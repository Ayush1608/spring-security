package com.self.springsecurityoathserver.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/h2-console/**").permitAll().anyRequest().authenticated().and().formLogin();

    // Added to display h2-database without errors
//    http.csrf().disable().headers().frameOptions().disable();
  }

  @Bean
  public UserDetailsService userDetails() {
    InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
    inMemoryUserDetailsManager.createUser(User.withUsername("user").password("1234").authorities("read").build());
    return inMemoryUserDetailsManager;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
