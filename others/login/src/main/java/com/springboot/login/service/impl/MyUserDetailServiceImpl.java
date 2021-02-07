package com.springboot.login.service.impl;


import com.springboot.login.model.MyUser;
import com.springboot.login.model.MyUserDetails;
import com.springboot.login.repository.LoginRepository;
import com.springboot.login.service.MyuserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailServiceImpl implements MyuserDetailsService {

  @Autowired
  LoginRepository loginRepository;

  @Override public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
    MyUser myUser = loginRepository.login(s);
    return new MyUserDetails(myUser.getUsername(), myUser.getPassword(), myUser.getGrantedAuthorities(), myUser.isEnabled());
  }
}
