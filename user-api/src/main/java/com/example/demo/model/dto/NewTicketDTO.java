package com.example.demo.model.dto;

import java.util.List;

public record NewTicketDTO(
    String acao,
    String objeto,
    String detalhes,
    Integer idCriador,
    Integer idDestinatario,
    List<String> observadores) {
}
