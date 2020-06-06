package com.self.csrfinspringboot.controller;


import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/app")
public class HelloController {

  @GetMapping("/hello")
  public String helloMessage() {
    return "Hello";
  }

  @PostMapping("/getAuthentication")
  public Principal getPricipal(Principal principal) {
    return principal;
  }
}
