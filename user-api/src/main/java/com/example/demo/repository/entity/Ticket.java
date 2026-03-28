package com.example.demo.repository.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket {

  public enum StatusType {
    PENDENTE("PENDENTE"),
    ANDAMENTO("ANDAMENTO"),
    RESOLVIDO("RESOLVIDO"),
    CANCELADO("CANCELADO");

    private String descricao;

    StatusType(String descricao) {
      this.descricao = descricao;
    }

    public String getDescricao() {
      return descricao;
    }
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 255)
  private String acao;

  @Column(nullable = false, length = 255)
  private String objeto;

  @Column(nullable = false, length = 255)
  private String status;

  @Column(columnDefinition = "TEXT")
  private String detalhes;

  @Column(columnDefinition = "TEXT")
  private String motivo;

  @Column(columnDefinition = "TEXT")
  private String observadores;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
  private LocalDateTime updatedAt;

  @JoinColumn(name = "id_criador", nullable = false)
  @ManyToOne
  private User criador;

  @JoinColumn(name = "id_destinatario", nullable = false)
  @ManyToOne
  private User destinatario;

  @JoinColumn(name = "id_responsavel")
  @ManyToOne
  private User responsavel;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAcao() {
    return acao;
  }

  public void setAcao(String acao) {
    this.acao = acao;
  }

  public String getObjeto() {
    return objeto;
  }

  public void setObjeto(String objeto) {
    this.objeto = objeto;
  }

  public StatusType getStatus() {
    return StatusType.valueOf(status);
  }

  public void setStatus(StatusType status) {
    this.status = status.getDescricao();
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getDetalhes() {
    return detalhes;
  }

  public void setDetalhes(String detalhes) {
    this.detalhes = detalhes;
  }

  public String getMotivo() {
    return motivo;
  }

  public void setMotivo(String motivo) {
    this.motivo = motivo;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public String getObservadores() {
    return observadores;
  }

  public void setObservadores(String observadores) {
    this.observadores = observadores;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public User getCriador() {
    return criador;
  }

  public void setCriador(User criador) {
    this.criador = criador;
  }

  public User getDestinatario() {
    return destinatario;
  }

  public void setDestinatario(User destinatario) {
    this.destinatario = destinatario;
  }

  public User getResponsavel() {
    return responsavel;
  }

  public void setResponsavel(User responsavel) {
    this.responsavel = responsavel;
  }

}
