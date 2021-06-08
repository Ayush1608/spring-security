package com.self.methodauthorization.service;


import java.util.List;


public interface ProductService {

  List<String> findProductsForUser(String username);

  String test1();

  String test2();

  String test3();

  List<String> test4(List<String> list);

  List<String> test5();
}
