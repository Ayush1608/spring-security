package com.self.springsecurityjdbc.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  DataSource dataSource;

  @Override protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//    <-- We have created a default schema here with default datasource (H2-> imported in pom)
//    We are leveraging spring security internal feature here-->
//    auth.jdbcAuthentication()
//    .dataSource(dataSource)
//    .withDefaultSchema()
//    .withUser(
//      User.withUsername("ayush")
//      .password("ayush1234")
//      .roles("Admin")
//    ).withUser(
//          User.withUsername("piyush")
//              .password("billi")
//              .roles("User")
//        );


//    <-- Here it will take from db we created by schema.sql and data.sql -->
    auth.jdbcAuthentication()
        .dataSource(dataSource);
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("/user").hasAnyRole("ADMIN", "USER")
        .antMatchers("/").permitAll()
        .and().formLogin();
  }
}
