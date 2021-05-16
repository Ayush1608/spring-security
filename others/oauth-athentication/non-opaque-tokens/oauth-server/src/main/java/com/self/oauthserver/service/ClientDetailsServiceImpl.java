package com.self.oauthserver.service;


import java.util.Optional;

import com.self.oauthserver.dto.SecurityClient;
import com.self.oauthserver.entity.AppClientDetails;
import com.self.oauthserver.repository.ClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;


@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

  @Autowired
  private ClientDetailsRepository clientDetailsRepository;

  @Override
  public ClientDetails loadClientByClientId(final String clientId) throws ClientRegistrationException {
    Optional<AppClientDetails> appClientDetails = clientDetailsRepository.findClientDetailsByClientId(clientId);
    return appClientDetails.map(SecurityClient::new).orElseThrow(() -> new ClientRegistrationException("Client Id nt found"));
  }
}
