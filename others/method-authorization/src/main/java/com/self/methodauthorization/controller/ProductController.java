package com.self.methodauthorization.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.self.methodauthorization.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {

  @Autowired
  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/products")
  public List<String> findProductsForUsername(Authentication authentication) {
    return productService.findProductsForUser(authentication.getName());
  }

  @GetMapping("/test1")
  public String test1() {
    return productService.test1();
  }

  @GetMapping("/test2")
  public String test2() {
    return productService.test2();
  }

  @GetMapping("/test3")
  public String test3() {
    return productService.test3();
  }

  @GetMapping("/test4")
  public List<String> test4() {
    List<String> list = new ArrayList<>();
    list.add("admin");
    list.add("user");
    list.add("mary");
    return productService.test4(list);
  }

  /**
   * Exception scenario
   * @return
   */
  @GetMapping("/test5")
  public List<String> test5() {
    List<String> list = Collections.unmodifiableList(Arrays.asList("admin", "user", "mary"));
    return productService.test4(list);
  }

  @GetMapping("/test6")
  public List<String> test6() {
    return productService.test5();
  }
}
