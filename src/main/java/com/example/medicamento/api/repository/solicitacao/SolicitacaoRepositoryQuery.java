package com.example.medicamento.api.repository.solicitacao;

import com.example.medicamento.api.model.Solicitacao;

public interface SolicitacaoRepositoryQuery {

	Solicitacao filtrarSolicitacaoAtual(String lote);

	
}