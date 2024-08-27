package com.example.SpringSecuritySample.auth;

import com.example.SpringSecuritySample.config.JwtService;
import com.example.SpringSecuritySample.token.Token;
import com.example.SpringSecuritySample.token.TokenRepository;
import com.example.SpringSecuritySample.token.TokenType;
import com.example.SpringSecuritySample.user.Role;
import com.example.SpringSecuritySample.user.User;
import com.example.SpringSecuritySample.user.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Autowired
  public AuthenticationService(UserRepository userRepository,
      PasswordEncoder passwordEncoder, JwtService jwtService,
      AuthenticationManager authenticationManager,
      TokenRepository tokenRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
    this.tokenRepository = tokenRepository;
  }

  public AuthenticationResponse register(RegisterRequest request) {
    User user = new User();
    user.setFirstname(request.getFirstname());
    user.setLastname(request.getLastname());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(Role.USER);
    User savedUser = userRepository.save(user);
    String jwtToken = jwtService.generateToken(user);
    saveUserToken(savedUser, jwtToken);

    return new AuthenticationResponse(jwtToken);
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
    String jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return new AuthenticationResponse(jwtToken);
  }

  private void revokeAllUserTokens(User user) {
    List<Token> validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
    if(validUserTokens.isEmpty()){
      return;
    }
    validUserTokens.forEach(token -> {
      token.setRevoked(true);
      token.setExpired(true);
    });

    tokenRepository.saveAll(validUserTokens);
  }

  private void saveUserToken(User user, String jwtToken) {
    Token token = new Token();
    token.setUser(user);
    token.setToken(jwtToken);
    token.setTokenType(TokenType.BEARER);
    token.setExpired(false);
    token.setRevoked(false);
    tokenRepository.save(token);
  }
}
