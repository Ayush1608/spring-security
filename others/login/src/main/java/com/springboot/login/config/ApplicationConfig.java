package com.springboot.login.config;


import static com.springboot.login.util.Constants.CONFIG_PATH;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.springboot.login.util.AppUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource(CONFIG_PATH + "application.properties")
public class ApplicationConfig implements ApplicationListener<ApplicationStartedEvent> {

  @Value("${static.queries.file.path}")
  private String queriesFilePath;

  @Value("${static.table.names.file.path}")
  private String tableNamesFilePath;

  private static Map<String, Map<String, Object>> queries;

  private static Map<String, String> tableNames;

  public static Map<String, String> getTableNames() {
    return tableNames;
  }

  public static Map<String, Map<String, Object>> getQueries() {
    return queries;
  }

  private void init() {
    initializeTableNames();
    initializeQueryMap();
  }

  private void initializeQueryMap() {
    queries = AppUtil.getDataFromFile(queriesFilePath, new TypeReference<Map<String, Map<String, Object>>>() {
    });
    queries = AppUtil.insertTableNameInQuery(queries);
  }

  private void initializeTableNames() {
    tableNames = AppUtil.getDataFromFile(tableNamesFilePath, new TypeReference<Map<String, String>>() {
    });
  }

  @Override public void onApplicationEvent(final ApplicationStartedEvent applicationStartedEvent) {
    init();
  }
}
