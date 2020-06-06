package com.self.csrfinspringboot.security;


import com.self.csrfinspringboot.security.csrf.CustomCSRFRepository;
import com.self.csrfinspringboot.security.filter.CustomCSRFFIlter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  CustomCSRFFIlter customCSRFFIlter;

  @Override protected void configure(final HttpSecurity http) throws Exception {
    super.configure(http);
    http.csrf( c ->{
      c.ignoringAntMatchers("/ayush/**");  //will ignore all url path with /ayush to check for csrf token.

//      Add Repository  #### To know how it internally works check Filter
      c.csrfTokenRepository(new CustomCSRFRepository());
    });

    http.addFilterAfter(customCSRFFIlter, CsrfFilter.class);
  }
}
