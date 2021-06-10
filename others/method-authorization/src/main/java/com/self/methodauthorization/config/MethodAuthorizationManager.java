package com.self.methodauthorization.config;


import java.util.List;

import com.self.methodauthorization.model.Document;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


/**
 * In DocumentPermissionEvaluator.java we have implemented an interface and we cannot define any other logic other than what we implement in
 * hasPermission() method.
 * In this class, we can define multiple functions as per our requirement and use them in various aspects. See eg in DocumentServiceImpl.java.
 * Also we need to provide application context in defaultMethodSecurityExpressionHandler (SecurityConfig.java) to enable
 * @methodAuthorizationManager.applySecurity(returnObject, 'write') like we have done in DocumentServiceImpl.java.
 */
@Component
public class MethodAuthorizationManager {


  public boolean applySecurity(List<Document> targetObject, String permission) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    boolean docsBelongToTheAuthUser = targetObject.stream()
                                                  .allMatch(d -> d.getUsername().equals(username));

    boolean hasProperAuthority = authentication.getAuthorities().stream()
                                               .anyMatch(g -> g.getAuthority().equals(permission));

    return docsBelongToTheAuthUser && hasProperAuthority;
  }
}
