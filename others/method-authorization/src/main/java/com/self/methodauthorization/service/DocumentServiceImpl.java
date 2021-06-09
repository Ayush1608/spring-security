package com.self.methodauthorization.service;


import java.util.Collections;
import java.util.List;

import com.self.methodauthorization.model.Document;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;


@Service
public class DocumentServiceImpl implements DocumentService {

  @Override
  @PostAuthorize("hasPermission(returnObject, 'write')")
  public List<Document> getDocuments(final String username) {
    Document document = new Document();
    document.setUsername("admin");
    return Collections.singletonList(document);
  }
}
