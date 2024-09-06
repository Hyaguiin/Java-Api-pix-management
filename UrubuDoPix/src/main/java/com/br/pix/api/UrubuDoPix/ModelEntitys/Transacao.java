package com.br.pix.api.UrubuDoPix.ModelEntitys;

import java.math.BigDecimal;
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
@Table(name = "transacao")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name = "valor", nullable = false)
	private BigDecimal valor;
	
	
	@Column(name = "data_transacao", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime data_transacao;
	
	@ManyToOne
	@JoinColumn(name = "id_chave_remetente", nullable = false)
	private ChavePix chaveRemetente;
	
	@ManyToOne
	@JoinColumn(name = "id_chave_destinatario", nullable = false)
	private ChavePix chaveDestinatario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getData_transacao() {
		return data_transacao;
	}

	public void setData_transacao(LocalDateTime data_transacao) {
		this.data_transacao = data_transacao;
	}

	public ChavePix getChaveRemetente() {
		return chaveRemetente;
	}

	public void setChaveRemetente(ChavePix chaveRemetente) {
		this.chaveRemetente = chaveRemetente;
	}

	public ChavePix getChaveDestinatario() {
		return chaveDestinatario;
	}

	public void setChaveDestinatario(ChavePix chaveDestinatario) {
		this.chaveDestinatario = chaveDestinatario;
	}

	


}

