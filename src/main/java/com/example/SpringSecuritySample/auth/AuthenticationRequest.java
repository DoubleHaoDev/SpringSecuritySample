package com.example.SpringSecuritySample.auth;

import java.util.Objects;

public class AuthenticationRequest {

  private String email;
  private String password;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AuthenticationRequest that = (AuthenticationRequest) o;
    return Objects.equals(email, that.email) && Objects.equals(password,
        that.password);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(email);
    result = 31 * result + Objects.hashCode(password);
    return result;
  }

  @Override
  public String toString() {
    return "AuthenticationRequest{" +
        "email='" + email + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
