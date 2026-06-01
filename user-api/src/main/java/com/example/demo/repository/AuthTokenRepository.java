package com.example.demo.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.AuthToken;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class AuthTokenRepository {

  private final EntityManager em;

  public AuthTokenRepository(EntityManager em) {
    this.em = em;
  }

  public Optional<AuthToken> findByToken(String token) {
    var q = em.createQuery("FROM AuthToken a WHERE a.token = :token", AuthToken.class)
        .setParameter("token", token)
        .setMaxResults(1);
    var list = q.getResultList();
    return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
  }

  @Transactional
  public void save(AuthToken t) {
    if (t.getToken() == null) {
      em.persist(t);
    } else {
      em.merge(t);
    }
  }

  @Transactional
  public void deleteByToken(String token) {
    em.createQuery("DELETE FROM AuthToken a WHERE a.token = :token")
        .setParameter("token", token)
        .executeUpdate();
  }

}
