package com.self.oauthserver.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;


@Entity(name = "client_details")
@Getter
public class AppClientDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String clientId;

  private String secret;

  private String grantType;

  private String scope;
}
