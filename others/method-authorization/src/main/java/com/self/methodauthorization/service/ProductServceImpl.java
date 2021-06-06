package com.self.methodauthorization.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


@Service
public class ProductServceImpl implements ProductService {


  /**
   * @PreAuthorize -> the authorization rules are validated before calling the protected method
   * @PostAuthorize -> the method is called, and only then the aspect validates the authorization rules. Generally, not used on the methods which
   * change something as the method is called but the response isn't delivered back to the caller if the rule doesn't comply.
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
  @Override public List<String> findProductsForUser(final String username) {
    return new ArrayList<>(Arrays.asList("iPhone", "Mac"));
  }
}
