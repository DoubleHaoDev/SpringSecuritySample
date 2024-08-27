package com.example.SpringSecuritySample.auth;

import java.util.Objects;

public class RegisterRequest {
  private String firstname;
  private String lastname;
  private String email;
  private String password;

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

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

    RegisterRequest that = (RegisterRequest) o;
    return Objects.equals(firstname, that.firstname) && Objects.equals(lastname,
        that.lastname) && Objects.equals(email, that.email) && Objects.equals(
        password, that.password);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(firstname);
    result = 31 * result + Objects.hashCode(lastname);
    result = 31 * result + Objects.hashCode(email);
    result = 31 * result + Objects.hashCode(password);
    return result;
  }

  @Override
  public String toString() {
    return "RegisterRequest{" +
        "firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
