package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.entity.Ticket;
import com.example.demo.repository.entity.Ticket.StatusType;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class TicketRepository {

  private final EntityManager em;

  public TicketRepository(EntityManager em) {
    this.em = em;
  }

  public Optional<Ticket> findById(Integer id) {
    return Optional.ofNullable(em.find(Ticket.class, id));
  }

  public List<Ticket> findAll() {
    return em.createQuery("FROM Ticket t", Ticket.class)
        .getResultList();
  }

  @Transactional
  public Ticket save(Ticket ticket) {
    ticket.setStatus(ticket.getStatus());
    if (ticket.getId() == null) {
      em.persist(ticket);
    } else {
      ticket = em.merge(ticket);
    }
    return ticket;
  }

  @Transactional
  public void delete(Ticket ticket) {
    em.remove(ticket);
  }

  // Métodos adicionais conforme necessidades do subsistema de tickets
  public List<Ticket> findByCriadorId(Integer criadorId) {
    return em.createQuery("FROM Ticket t WHERE t.criador.id = :criadorId", Ticket.class)
        .setParameter("criadorId", criadorId)
        .getResultList();
  }

  public List<Ticket> findByDestinatarioId(Integer destinatarioId) {
    return em.createQuery("FROM Ticket t WHERE t.destinatario.id = :destinatarioId", Ticket.class)
        .setParameter("destinatarioId", destinatarioId)
        .getResultList();
  }

  public List<Ticket> findByStatus(StatusType status) {
    return em.createQuery("FROM Ticket t WHERE t.status = :status", Ticket.class)
        .setParameter("status", status)
        .getResultList();
  }

}
