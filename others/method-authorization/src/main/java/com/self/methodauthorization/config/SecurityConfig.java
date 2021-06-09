package com.self.methodauthorization.config;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true/*,
                            jsr250Enabled = true,
                            securedEnabled = true*/)
/*
 * The prePostEnabled property enables Spring Security pre/post (@PreAuthorize, @PostAutorize, @PreFilter, @PostFilter) annotations
 * The securedEnabled property determines if the @Secured annotation should be enabled
 * The jsr250Enabled property allows us to use the @RoleAllowed annotation'
 *
 *
 */
public class SecurityConfig extends /*WebSecurityConfigurerAdapter*/ GlobalMethodSecurityConfiguration {

//  @Override protected void configure(final HttpSecurity http) throws Exception {
//    http.httpBasic();
//    http.authorizeRequests()
//        .anyRequest()
//        .authenticated();
//  }

//  @Override protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication()
//        .withUser("user")
//        .password("user")
//        .authorities("read")
//        .and()
//        .withUser("admin")
//        .password("admin")
//        .authorities("write");
//  }

  @Bean
  public UserDetailsService uds () {
    UserDetailsManager uds = new InMemoryUserDetailsManager();

    UserDetails u1 =
      User.withUsername("admin")
          .password("admin")
          .authorities("write")
          .build();

    UserDetails u2 =
      User.withUsername("user")
          .password("user")
          .authorities("read")
          .build();

    uds.createUser(u1);
    uds.createUser(u2);

    return uds;
  }
  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override protected MethodSecurityExpressionHandler createExpressionHandler() {
    DefaultMethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
    defaultMethodSecurityExpressionHandler.setPermissionEvaluator(getPermissionEvaluator());
    return defaultMethodSecurityExpressionHandler;
  }

  @Bean
  public PermissionEvaluator getPermissionEvaluator() {
    return new DocumentPersmissionEvaluator();
  }
}
