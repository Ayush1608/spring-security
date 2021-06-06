package com.self.methodauthorization.controller;


import java.util.List;

import com.self.methodauthorization.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
