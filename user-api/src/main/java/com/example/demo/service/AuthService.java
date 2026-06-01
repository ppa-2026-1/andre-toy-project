package com.example.demo.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.repository.AuthTokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.AuthToken;
import com.example.demo.repository.entity.User;

@Service
public class AuthService { // Singleton
  private final AuthTokenRepository tokenRepository;
  private final UserRepository userRepository;
  private final SecureRandom random = new SecureRandom();
  private final long tokenTtlSeconds;

  public AuthService(AuthTokenRepository tokenRepository, UserRepository userRepository,
      @Value("${app.auth.token.ttl-seconds:3600}") long tokenTtlSeconds) {
    this.tokenRepository = tokenRepository;
    this.userRepository = userRepository;
    this.tokenTtlSeconds = tokenTtlSeconds;
  }

  public String validateToken(String token) {
    if (token == null || token.isBlank()) {
      return null;
    }

    var opt = tokenRepository.findByToken(token);
    if (opt.isEmpty()) {
      return null;
    }

    AuthToken t = opt.get();
    if (t.getExpiresAt().isBefore(LocalDateTime.now())) {
      // token expirado: apagar e retornar null
      tokenRepository.deleteByToken(token);
      return null;
    }

    return t.getHandle();
  }

  public String login(String username, String password) {
    if (username == null || username.isBlank() || password == null) {
      throw new IllegalArgumentException("username/password inválidos");
    }

    // buscar usuário por handle ou email
    User user = userRepository.findByHandle(username)
        .or(() -> userRepository.findByEmail(username))
        .orElseThrow(() -> new IllegalArgumentException("Usuário ou senha inválidos"));

    var encoder = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    if (!encoder.matches(password, user.getPassword())) {
      throw new IllegalArgumentException("Usuário ou senha inválidos");
    }

    // gerar token
    byte[] bytes = new byte[32];
    random.nextBytes(bytes);
    String token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);

    AuthToken t = new AuthToken();
    t.setToken(token);
    t.setHandle(user.getHandle());
    t.setCreatedAt(LocalDateTime.now());
    t.setExpiresAt(LocalDateTime.now().plusSeconds(tokenTtlSeconds));

    tokenRepository.save(t);

    return token;
  }

  public void logout(String token) {
    if (token == null || token.isBlank()) {
      return;
    }
    tokenRepository.deleteByToken(token);
  }

}
