package com.self.springsecurityjpa.service;


import java.util.Optional;

import com.self.springsecurityjpa.model.MyUserDetails;
import com.self.springsecurityjpa.model.entity.MyUser;
import com.self.springsecurityjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override public UserDetails loadUserByUsername(final String s) throws UsernameNotFoundException {
    Optional<MyUser> user = userRepository.findByUsername(s);
    return new MyUserDetails(user.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + user)));
  }
}
