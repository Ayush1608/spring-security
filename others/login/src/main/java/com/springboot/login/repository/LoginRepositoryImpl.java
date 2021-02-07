package com.springboot.login.repository;


import java.util.Collections;

import com.springboot.login.model.MyUser;
import com.springboot.login.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;


@Repository
public class LoginRepositoryImpl implements LoginRepository {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override public MyUser login(final String username) {
    String finalQuery = AppUtil.getQueryFromQueryMap("login").replace("%username%", username);

    MyUser myUser = new MyUser();

    jdbcTemplate.queryForObject(finalQuery, (resultSet, i) -> {
      myUser.setEnabled(resultSet.getBoolean("active"));
      myUser.setUsername(resultSet.getString("user_name"));
      myUser.setPassword(resultSet.getString("password"));
      myUser.setGrantedAuthorities(Collections.singletonList(new SimpleGrantedAuthority(resultSet.getString("roles"))));
      return null;
    });
    return myUser;
  }

  @Override public void saveOtp(final String username, final String otp) {
    String query = AppUtil.getQueryFromQueryMap("saveOtp").replaceAll("%username%", username).replaceAll("%otp", otp);
    jdbcTemplate.update(query);
  }
}
