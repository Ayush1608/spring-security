package com.self.springsecurityjpa.repository;


import java.util.Optional;

import com.self.springsecurityjpa.model.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<MyUser, Integer> {
  Optional<MyUser> findByUsername(String username);
}
