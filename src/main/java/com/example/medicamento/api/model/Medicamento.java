package com.example.medicamento.api.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "medicamento")
public class Medicamento {

	@Id
	private String lote;

	@NotNull
	@Column(name = "principio_ativo")
	private String principioAtivo;

	@NotNull
	private String dosagem;

	@NotNull
	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;

	@Column(name = "nome_comercial")
	private String nomeComercial;

	@Column(name = "outras_especificacoes")
	private String outrasEspecificacoes;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_tipo_medicamento")
	private TipoMedicamento tipo;

	private String laboratorio;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_tipo_status_medicamento")
	private Status status;

	@Column(name = "ob_exclusao")
	private String obsExclusao;

	@Column(name = "ic_uso_veterinario")
	private String usoVeterinario;

	@OneToOne(mappedBy = "medicamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
	private Estoque estoque;

	@Transient
	private Solicitacao solicitacaoAtual;

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getPrincipioAtivo() {
		return principioAtivo;
	}

	public void setPrincipioAtivo(String principioAtivo) {
		this.principioAtivo = principioAtivo;
	}

	public String getDosagem() {
		return dosagem;
	}

	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getNomeComercial() {
		return nomeComercial;
	}

	public void setNomeComercial(String nomeComercial) {
		this.nomeComercial = nomeComercial;
	}

	public String getOutrasEspecificacoes() {
		return outrasEspecificacoes;
	}

	public void setOutrasEspecificacoes(String outrasEspecificacoes) {
		this.outrasEspecificacoes = outrasEspecificacoes;
	}

	public TipoMedicamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoMedicamento tipo) {
		this.tipo = tipo;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getObsExclusao() {
		return obsExclusao;
	}

	public void setObsExclusao(String obsExclusao) {
		this.obsExclusao = obsExclusao;
	}

	public String getUsoVeterinario() {
		return usoVeterinario;
	}

	public void setUsoVeterinario(String usoVeterinario) {
		this.usoVeterinario = usoVeterinario;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Solicitacao getSolicitacaoAtual() {
		return solicitacaoAtual;
	}

	public void setSolicitacaoAtual(Solicitacao solicitacaoAtual) {
		this.solicitacaoAtual = solicitacaoAtual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lote == null) ? 0 : lote.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicamento other = (Medicamento) obj;
		if (lote == null) {
			if (other.lote != null)
				return false;
		} else if (!lote.equals(other.lote))
			return false;
		return true;
	}

	

}
