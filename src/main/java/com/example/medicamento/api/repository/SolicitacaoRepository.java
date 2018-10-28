package com.example.medicamento.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medicamento.api.model.Solicitacao;
import com.example.medicamento.api.repository.solicitacao.SolicitacaoRepositoryQuery;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long>, SolicitacaoRepositoryQuery{
}