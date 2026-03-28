package com.example.demo.model.dto;

import com.example.demo.repository.entity.Ticket.StatusType;

public class UpdateStatusDTO {

    private StatusType status;
    private Integer responsavelId;
    private String motivo;

    // Getters and Setters
    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Integer getResponsavelId() {
        return responsavelId;
    }

    public void setResponsavelId(Integer responsavelId) {
        this.responsavelId = responsavelId;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
