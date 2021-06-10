package com.self.methodauthorization.service;


import javax.annotation.security.RolesAllowed;
import java.util.Collections;
import java.util.List;

import com.self.methodauthorization.model.Document;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;


@Service
public class DocumentServiceImpl implements DocumentService {

  /**
   * @Secured and @RolesAllowed are the same the only difference is @RolesAllowed is a standard annotation (i.e. not only spring security) whereas
   * @Secured is spring security only.
   */
  @Override
//  @PostAuthorize("hasPermission(returnObject, 'write')")
//  @PostAuthorize("@methodAuthorizationManager.applySecurity(returnObject, 'write')")
//  @Secured("ROLE_ADMIN")
  @RolesAllowed("ROLE_ADMIN")
  public List<Document> getDocuments(final String username) {
    Document document = new Document();
    document.setUsername("admin");
    return Collections.singletonList(document);
  }
}
