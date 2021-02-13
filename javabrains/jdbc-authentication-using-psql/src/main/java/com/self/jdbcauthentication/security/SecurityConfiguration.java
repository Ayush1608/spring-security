package com.self.jdbcauthentication.security;


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
    /**
     * Considering we have a table "users" in our database with below schema
     *
     *                                      Table "public.users"
     *   Column   |         Type          | Collation | Nullable |              Default
     * -----------+-----------------------+-----------+----------+-----------------------------------
     *  id        | integer               |           | not null | nextval('users_id_seq'::regclass)
     *  user_name | character varying(50) |           |          |
     *  password  | character varying(50) |           |          |
     *  active    | boolean               |           |          |
     *  roles     | character varying(10) |           |          |
     * Indexes:
     *     "users_pkey" PRIMARY KEY, btree (id)
     *     "users_user_name_key" UNIQUE CONSTRAINT, btree (user_name)
     */

    auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("select user_name as username, password, active as enabled from users where user_name = ?")
        .authoritiesByUsernameQuery("select user_name as username, roles as authority from users where user_name = ?")
        .passwordEncoder(getPasswordEncoder());
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/admin").hasRole("READWRITE")
        .antMatchers("/user").hasAnyRole("ADMIN", "READ")
        .antMatchers("/").permitAll()
        .and().formLogin();
  }
}
