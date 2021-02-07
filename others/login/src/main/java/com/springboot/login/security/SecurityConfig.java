package com.springboot.login.security;


import com.springboot.login.security.filters.CustomAuthFilter;
import com.springboot.login.security.provider.KeyBasedAuthProvider;
import com.springboot.login.security.provider.UsernamePasswordAuthProvider;
import com.springboot.login.service.MyuserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  CustomAuthFilter customAuthFilter;

  @Autowired
  MyuserDetailsService myuserDetailsService;

  @Autowired
  UsernamePasswordAuthProvider usernamePasswordAuthProvider;

  @Autowired
  KeyBasedAuthProvider keyBasedAuthProvider;

  @Override protected void configure(final AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(usernamePasswordAuthProvider).authenticationProvider(keyBasedAuthProvider);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override protected void configure(final HttpSecurity http) throws Exception {
    http.httpBasic().and().addFilterAt(customAuthFilter, BasicAuthenticationFilter.class)
        .csrf().disable().authorizeRequests().antMatchers("/authenticate", "/hello")
        .permitAll().antMatchers("/app/**").authenticated().and()
//        .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()
        .logout().logoutSuccessUrl("/authenticate")
        .and().oauth2Login();
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
