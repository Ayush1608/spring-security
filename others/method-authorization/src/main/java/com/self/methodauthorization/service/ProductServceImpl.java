package com.self.methodauthorization.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;


@Service
public class ProductServceImpl implements ProductService {


  /**
   * @PreAuthorize -> the authorization rules are validated before calling the protected method
   * @PostAuthorize -> the method is called, and only then the aspect validates the authorization rules. Generally, not used on the methods which
   *                   change something as the method is called but the response isn't delivered back to the caller if the rule doesn't comply.
   *                -> Generally used when return object is to be checked with rule.
   *
   * @PreFilter -> The method needs to have the parameter of type Collection or array
   *            -> The aspects intercepts the method call and validates the values inside the collection.
   *            -> The method always runs but only for the values which comply the rules.
   *
   * @PostFilter -> returned value needs to be a Collection or an array
   *             -> the aspect applies the authorization rules and returns only the values(in the return collection) that comply
   */
//  @PreAuthorize("hasAuthority('write')")
  @PreAuthorize("#username == 'user'")
  @Override
  public List<String> findProductsForUser(final String username) {
    return new ArrayList<>(Arrays.asList("iPhone", "Mac"));
  }

  @PreAuthorize("hasAuthority('read')")
  @Override
  public String test1() {
    System.out.println("TEST 1");
    return "TEST1";
  }

  @PostAuthorize("hasAuthority('read')")
  @Override
  public String test2() {
    System.out.println("TEST 2");
    return "TEST2";
  }

  @PostAuthorize("returnObject == authentication.name")
  @Override
  public String test3() {
    System.out.println("TEST 3");
    // select some data from a database
    return "admin";
  }

  /**
   * @PreFilter does not create a new object when it filters the values. It actually uses the same object and hence if an immutable list is passed
   * to it, it won't be able to filter and UnSupportedOperationException will occur.
   * /test5 from Controller will throw the exception.
   * @param list
   * @return
   */
  @PreFilter("filterObject != authentication.name")
  @Override
  public List<String> test4(List<String> list) {
    System.out.println(list);
    return list;
  }

  /**
   * @PreFilter does not create a new object when it filters the values. It actually uses the same object and hence if an immutable list is passed
   * to it, it won't be able to filter and UnSupportedOperationException will occur.
   * @return
   */
  @PostFilter("filterObject != authentication.name")
  @Override
  public List<String> test5() {
    List<String> list = new ArrayList<>();
    list.add("admin");
    list.add("user");
    list.add("mary");
    return list;
  }

}
