package com.self.methodauthorization.service;


import java.util.List;

import com.self.methodauthorization.model.Document;


public interface DocumentService {

  List<Document> getDocuments(String username);
}
