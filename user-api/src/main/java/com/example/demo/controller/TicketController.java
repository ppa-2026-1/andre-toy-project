package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TicketService;
import com.example.demo.model.dto.NewTicketDTO;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.entity.Ticket;
import com.example.demo.repository.entity.Ticket.StatusType;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

  private final TicketRepository ticketRepository;
  private final TicketService ticketService;

  public TicketController(
      TicketRepository ticketRepository,
      TicketService ticketService) {
    this.ticketRepository = ticketRepository;
    this.ticketService = ticketService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code = HttpStatus.CREATED)
  public void newTicket(@RequestBody NewTicketDTO newTicket) {
    ticketService.registerNewTicket(newTicket);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Ticket>> getTickets() {
    return ResponseEntity.ok(ticketRepository.findAll());
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Ticket> getTicketById(@PathVariable Integer id) {
    return ticketService.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PatchMapping(value = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Ticket> updateTicketStatus(@PathVariable Integer id, @RequestBody Map<String, Object> body) {
    String status = (String) body.get("status");
    Integer responsavelId = body.get("responsavelId") != null ? (Integer) body.get("responsavelId") : null;
    String motivo = body.get("motivo") != null ? (String) body.get("motivo") : null;
    Ticket updated = ticketService.updateStatus(id, StatusType.valueOf(status), responsavelId, motivo);
    return ResponseEntity.ok(updated);
  }
}
