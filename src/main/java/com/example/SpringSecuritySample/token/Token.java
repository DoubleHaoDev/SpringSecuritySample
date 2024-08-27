package com.example.SpringSecuritySample.token;

import com.example.SpringSecuritySample.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Token {

  @Id
  @GeneratedValue
  private Integer id;
  private String token;
  @Enumerated(EnumType.STRING)
  private TokenType tokenType;
  private boolean expired;
  private boolean revoked;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public TokenType getTokenType() {
    return tokenType;
  }

  public void setTokenType(TokenType tokenType) {
    this.tokenType = tokenType;
  }

  public boolean isExpired() {
    return expired;
  }

  public void setExpired(boolean expired) {
    this.expired = expired;
  }

  public boolean isRevoked() {
    return revoked;
  }

  public void setRevoked(boolean revoked) {
    this.revoked = revoked;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Token token1 = (Token) o;
    return expired == token1.expired && revoked == token1.revoked && Objects.equals(id,
        token1.id) && Objects.equals(token, token1.token) && tokenType == token1.tokenType
        && Objects.equals(user, token1.user);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(id);
    result = 31 * result + Objects.hashCode(token);
    result = 31 * result + Objects.hashCode(tokenType);
    result = 31 * result + Boolean.hashCode(expired);
    result = 31 * result + Boolean.hashCode(revoked);
    result = 31 * result + Objects.hashCode(user);
    return result;
  }

  @Override
  public String toString() {
    return "Token{" +
        "id=" + id +
        ", token='" + token + '\'' +
        ", tokenType=" + tokenType +
        ", expired=" + expired +
        ", revoked=" + revoked +
        ", user=" + user +
        '}';
  }
}
