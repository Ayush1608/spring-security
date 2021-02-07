package com.springboot.login.controller;


import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/app")
public class HelloController {

  @GetMapping("/hello")
  public ResponseEntity<String> helloMessage() {
    return ResponseEntity.ok("hello");
  }

  @GetMapping("/getAuthentication")
  public Authentication getPricipal(Principal principal) {
    return SecurityContextHolder.getContext().getAuthentication();
  }

//  @GetMapping("/getAuthentication")
//  public Map<String, Object> getPricipal(@AuthenticationPrincipal OAuth2User principal) {
//    return Collections.singletonMap("name", principal.getAttribute("name"));
//  }
}
