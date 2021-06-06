package com.self.resourceserverwithdsl.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello() {
    return "hello";
  }

  @GetMapping("/demo")
  public String demo() {
    return "demo";
  }
}
