package com.self.methodauthorization.config;


import java.io.Serializable;
import java.util.List;

import com.self.methodauthorization.model.Document;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;


public class DocumentPersmissionEvaluator implements PermissionEvaluator {

  @Override public boolean hasPermission(final Authentication authentication, final Object targetObject, final Object permission) {
    List<Document> returnedList = (List<Document>) targetObject;
    String username = authentication.getName();
    String auth = (String) permission;

    boolean docsBelongToTheAuthUser = returnedList.stream()
                                                 .allMatch(d -> d.getUsername().equals(username));

    boolean hasProperAuthority = authentication.getAuthorities().stream()
                                               .anyMatch(g -> g.getAuthority().equals(auth));

    return docsBelongToTheAuthUser && hasProperAuthority;
  }

  @Override public boolean hasPermission(final Authentication authentication, final Serializable targetId, final String type,
                                         final Object permission) {
    return false;
  }
}
