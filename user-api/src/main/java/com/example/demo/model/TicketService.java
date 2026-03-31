package com.example.demo.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.dto.NewTicketDTO;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.entity.Ticket;
import com.example.demo.repository.entity.User;
import com.example.demo.repository.entity.Ticket.StatusType;

@Service
public class TicketService {

  private final TicketRepository ticketRepository;
  private final UserRepository userRepository;

  public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
    this.ticketRepository = ticketRepository;
    this.userRepository = userRepository;
  }

  public Ticket registerNewTicket(NewTicketDTO newTicketDTO) {
    // Validações
    if (newTicketDTO.acao() == null || newTicketDTO.acao().isEmpty()) {
      throw new IllegalArgumentException("Ação é obrigatória");
    }
    if (newTicketDTO.objeto() == null || newTicketDTO.objeto().isEmpty()) {
      throw new IllegalArgumentException("Objeto é obrigatório");
    }
    if (newTicketDTO.idCriador() == null) {
      throw new IllegalArgumentException("Criador é obrigatório");
    }

    // Buscar criador
    User criador = userRepository.findById(newTicketDTO.idCriador())
        .orElseThrow(() -> new IllegalArgumentException("Criador não encontrado"));

    // Destinatário: se não informado, mesmo que criador
    User destinatario = criador;
    if (newTicketDTO.idDestinatario() != null) {
      destinatario = userRepository.findById(newTicketDTO.idDestinatario())
          .orElseThrow(() -> new IllegalArgumentException("Destinatário não encontrado"));
    }

    // Criar ticket
    Ticket ticket = new Ticket();
    ticket.setAcao(newTicketDTO.acao());
    ticket.setObjeto(newTicketDTO.objeto());
    ticket.setDetalhes(newTicketDTO.detalhes());
    ticket.setStatus(StatusType.PENDENTE);
    ticket.setCriador(criador);
    ticket.setDestinatario(destinatario);
    ticket.setObservadores(
        String.join(",", newTicketDTO.observadores() != null ? newTicketDTO.observadores() : List.of()));
    ticket.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
    ticket.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

    return ticketRepository.save(ticket);
  }

  public Optional<Ticket> findById(Integer id) {
    return ticketRepository.findById(id);
  }

  public List<Ticket> findAll() {
    return ticketRepository.findAll();
  }

  public List<Ticket> findByCriadorId(Integer criadorId) {
    return ticketRepository.findByCriadorId(criadorId);
  }

  public List<Ticket> findByDestinatarioId(Integer destinatarioId) {
    return ticketRepository.findByDestinatarioId(destinatarioId);
  }

  public List<Ticket> findByStatus(StatusType status) {
    return ticketRepository.findByStatus(status);
  }

  public Ticket updateStatus(Integer ticketId, StatusType newStatus, Integer responsavelId, String motivo) {
    Ticket ticket = ticketRepository.findById(ticketId)
        .orElseThrow(() -> new IllegalArgumentException("Ticket não encontrado"));

    // Validações
    if (newStatus == StatusType.CANCELADO && (motivo == null || motivo.isEmpty())) {
      throw new IllegalArgumentException("Motivo é obrigatório para cancelamento");
    }
    if (newStatus == StatusType.ANDAMENTO && responsavelId == null) {
      throw new IllegalArgumentException("Responsável é obrigatório para colocar em andamento");
    }

    ticket.setStatus(newStatus);
    ticket.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

    if (newStatus == StatusType.ANDAMENTO) {
      User responsavel = userRepository.findById(responsavelId)
          .orElseThrow(() -> new IllegalArgumentException("Responsável não encontrado"));
      ticket.setResponsavel(responsavel);
      ticket.setMotivo(null);
    } else if (newStatus == StatusType.CANCELADO) {
      ticket.setMotivo(motivo);
    } else {
      ticket.setMotivo(null);
    }

    return ticketRepository.save(ticket);
  }

  public void deleteTicket(Integer ticketId) {
    Ticket ticket = ticketRepository.findById(ticketId)
        .orElseThrow(() -> new IllegalArgumentException("Ticket não encontrado"));
    ticketRepository.delete(ticket);
  }
}
