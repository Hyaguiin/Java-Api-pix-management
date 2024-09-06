package com.br.pix.api.UrubuDoPix.ModelEntitys;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "senha_hash", nullable = false)
	private String senha_hash;

	@Column(name = "data_criacao", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime data_criacao;
	
	
	
	@PrePersist
	public void onCreate() {
		LocalDateTime now = LocalDateTime.now();
		this.data_criacao = now;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha_hash() {
		return senha_hash;
	}

	public void setSenha_hash(String senha_hash) {
		this.senha_hash = senha_hash;
	}

	public LocalDateTime getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(LocalDateTime data_criacao) {
		this.data_criacao = data_criacao;
	}

	public Integer getId() {
		return id;
	}

}
