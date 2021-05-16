package com.self.oauthserver.repository;


import java.util.Optional;

import com.self.oauthserver.entity.AppClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientDetailsRepository extends JpaRepository<AppClientDetails, Integer> {

  Optional<AppClientDetails> findClientDetailsByClientId(String clientId);
}
