package com.self.corsspringboot.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

  @PostMapping("/")
  @CrossOrigin("*")
  public ResponseEntity getHelloMessage() {
    return ResponseEntity.ok("Hello");
  }
}
