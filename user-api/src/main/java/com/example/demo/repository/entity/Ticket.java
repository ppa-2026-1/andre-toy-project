package com.example.demo.repository.entity;

import java.sql.Timestamp;

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
    PENDENTE,
    ANDAMENTO,
    RESOLVIDO,
    CANCELADO;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 255)
  private String acao;

  @Column(nullable = false, length = 255)
  private String objeto;

  @Column(nullable = false, length = 255)
  private StatusType status;

  @Column(columnDefinition = "TEXT")
  private String detalhes;

  @Column(columnDefinition = "TEXT")
  private String motivo;

  @Column(columnDefinition = "TEXT")
  private String observadores;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
  private Timestamp createdAt;

  @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
  private Timestamp updatedAt;

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
    return status;
  }

  public void setStatus(StatusType status) {
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

  public String getObservadores() {
    return observadores;
  }

  public void setObservadores(String observadores) {
    this.observadores = observadores;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
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
