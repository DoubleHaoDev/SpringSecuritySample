package com.example.SpringSecuritySample.token;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Integer> {

  @Query("SELECT t FROM Token t JOIN User u ON t.user.id = u.id "
      + "WHERE u.id = :userId AND (t.expired = false or t.revoked = false)")
  List<Token> findAllValidTokensByUser(Integer userId);

  Optional<Token> findByToken(String token);
}
