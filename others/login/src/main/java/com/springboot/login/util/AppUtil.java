package com.springboot.login.util;


import static com.springboot.login.util.Constants.CONFIG_PATH;
import static com.springboot.login.util.Constants.QUERY;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.login.config.ApplicationConfig;
import org.springframework.util.ResourceUtils;


public class AppUtil {

  public static <T> T getDataFromFile(String path, TypeReference<T> typeReference) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      File file = ResourceUtils.getFile(CONFIG_PATH + path);
      return objectMapper.readValue(file, typeReference);
    }
    catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Map<String, Map<String, Object>> insertTableNameInQuery(Map<String, Map<String, Object>> queryMap) {
    queryMap.forEach((moduleName, values) -> {
      String query = (String) values.get(QUERY);
      Map<String, String> replacements = (Map<String, String>) values.get("replacements");
      for (Map.Entry<String, String> replacement : replacements.entrySet()) {
        String tableName = ApplicationConfig.getTableNames().get(replacement.getValue());
        query = query.replace(replacement.getKey(), tableName);
      }
      values.replace(QUERY, query);
    });
    return queryMap;
  }

  public static String getQueryFromQueryMap(String module) {
    return (String) ApplicationConfig.getQueries().get(module).get(QUERY);
  }
}
