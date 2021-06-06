package com.self.methodauthorization.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


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
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override protected void configure(final HttpSecurity http) throws Exception {
    http.httpBasic();
    http.authorizeRequests()
        .anyRequest()
        .authenticated();
  }

  @Override protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("user")
        .password("user")
        .authorities("read")
        .and()
        .withUser("admin")
        .password("admin")
        .authorities("write");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
