package com.springboot.login.repository;


import com.springboot.login.model.MyUser;


public interface LoginRepository {

  MyUser login(String username);
  void saveOtp(String username, String otp);
}
