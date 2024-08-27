package com.example.SpringSecuritySample.auth;


import java.util.Objects;

public class AuthenticationResponse {

  public AuthenticationResponse(String token) {
    this.token = token;
  }

  private String token;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AuthenticationResponse that = (AuthenticationResponse) o;
    return Objects.equals(token, that.token);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(token);
  }

  @Override
  public String toString() {
    return "AuthenticationResponse{" +
        "token='" + token + '\'' +
        '}';
  }
}
