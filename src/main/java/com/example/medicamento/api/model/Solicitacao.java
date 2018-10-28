package com.example.medicamento.api.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "solicitacao_medicamento")
public class Solicitacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_solicitacao_medicamento")
	private Long id;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lote")
	private Medicamento medicamento;
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@Column(name = "id_pessoa")
	private Long idPessoa;
	
	@Column(name = "data_final")
	private Date dataFinal;
	
	@Column(name = "confirmada")
	private Boolean confirmada;
	
	@Column(name = "excluida")
	private Boolean excluida;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Boolean getConfirmada() {
		return confirmada;
	}

	public void setConfirmada(Boolean confirmada) {
		this.confirmada = confirmada;
	}

	public Boolean getExcluida() {
		return excluida;
	}

	public void setExcluida(Boolean excluida) {
		this.excluida = excluida;
	}
}
