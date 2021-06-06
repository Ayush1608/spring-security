package com.self.methodauthorization.service;


import java.util.List;


public interface ProductService {

  List<String> findProductsForUser(String username);
}
