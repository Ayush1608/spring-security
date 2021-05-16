package com.self.oauthserver.dto;


import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import com.self.oauthserver.entity.AppClientDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;


public class SecurityClient implements ClientDetails {

  private final AppClientDetails appClientDetails;

  public SecurityClient(AppClientDetails appClientDetails) {
    this.appClientDetails = appClientDetails;
  }

  @Override public String getClientId() {
    return this.appClientDetails.getClientId();
  }

  @Override public Set<String> getResourceIds() {
    return null;
  }

  @Override public boolean isSecretRequired() {
    return true;
  }

  @Override public String getClientSecret() {
    return this.appClientDetails.getSecret();
  }

  @Override public boolean isScoped() {
    return true;
  }

  @Override public Set<String> getScope() {
    return Collections.singleton(this.appClientDetails.getScope());
  }

  @Override public Set<String> getAuthorizedGrantTypes() {
    return Collections.singleton(this.appClientDetails.getGrantType());
  }

  @Override public Set<String> getRegisteredRedirectUri() {
    return null;
  }

  @Override public Collection<GrantedAuthority> getAuthorities() {
    return Collections.singletonList(this.appClientDetails::getScope);
  }

  @Override public Integer getAccessTokenValiditySeconds() {
    return null;
  }

  @Override public Integer getRefreshTokenValiditySeconds() {
    return null;
  }

  @Override public boolean isAutoApprove(final String scope) {
    return false;
  }

  @Override public Map<String, Object> getAdditionalInformation() {
    return null;
  }
}
