package com.example.SpringSecuritySample.auth;

import com.example.SpringSecuritySample.config.JwtService;
import com.example.SpringSecuritySample.user.Role;
import com.example.SpringSecuritySample.user.User;
import com.example.SpringSecuritySample.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Autowired
  public AuthenticationService(UserRepository userRepository,
      PasswordEncoder passwordEncoder, JwtService jwtService,
      AuthenticationManager authenticationManager) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
  }

  public AuthenticationResponse register(RegisterRequest request) {
    User user = new User();
    user.setFirstname(request.getFirstname());
    user.setLastname(request.getLastname());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(Role.USER);
    userRepository.save(user);

    String token = jwtService.generateToken(user);
    return new AuthenticationResponse(token);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );

    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new RuntimeException("User not found"));
    String token = jwtService.generateToken(user);
    return new AuthenticationResponse(token);
  }
}
