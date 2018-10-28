package com.example.medicamento.api.repository.medicamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.example.medicamento.api.model.Medicamento;
import com.example.medicamento.api.model.Medicamento_;
import com.example.medicamento.api.model.Solicitacao;
import com.example.medicamento.api.model.Status_;
import com.example.medicamento.api.repository.SolicitacaoRepository;
import com.example.medicamento.api.repository.filter.MedicamentoFilter;

public class MedicamentoRepositoryImpl implements MedicamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	@Override
	public Page<Medicamento> filtrar(MedicamentoFilter medicamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Medicamento> criteria = builder.createQuery(Medicamento.class);
		Root<Medicamento> root = criteria.from(Medicamento.class);

		Predicate[] predicates = criarRestricoes(medicamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Medicamento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		adicionarSolicitacaoAtual(query.getResultList());
		return new PageImpl<>(query.getResultList(), pageable, total(medicamentoFilter));
	}

	private void adicionarSolicitacaoAtual(List<Medicamento> lista) {
		for (Medicamento medicamento : lista) {
			if(medicamento.getStatus().getDescricao().equals("Reservado")) {
				Solicitacao solicitacao = solicitacaoRepository.filtrarSolicitacaoAtual(medicamento.getLote());
				solicitacao.setMedicamento(null);
				medicamento.setSolicitacaoAtual(solicitacao);
			}
		}
	}
	
	private Predicate[] criarRestricoes(MedicamentoFilter medicamentoFilter, CriteriaBuilder builder,
			Root<Medicamento> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(medicamentoFilter.getDescricao())) {
			predicates.add(builder.or(
					builder.like(builder.lower(root.get(Medicamento_.principioAtivo)),
							"%" + medicamentoFilter.getDescricao().toLowerCase() + "%"),
					builder.like(builder.lower(root.get(Medicamento_.nomeComercial)),
							"%" + medicamentoFilter.getDescricao().toLowerCase() + "%")));
		}

		if (!StringUtils.isEmpty(medicamentoFilter.getStatus())) {
			predicates.add(builder.equal(builder.lower(root.get(Medicamento_.status).get(Status_.descricao)), medicamentoFilter.getStatus().toLowerCase()));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Medicamento> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(MedicamentoFilter medicamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Medicamento> root = criteria.from(Medicamento.class);

		Predicate[] predicates = criarRestricoes(medicamentoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	@Override
	public List<String> filtrarNomes(String nome) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<Medicamento> root = criteria.from(Medicamento.class);
		
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(nome)) {
			predicates.add(builder.or(
					builder.like(builder.lower(root.get(Medicamento_.principioAtivo)),
							nome.toLowerCase() + "%")));
		}
		
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		criteria.select(root.get(Medicamento_.principioAtivo)).distinct(true);
		TypedQuery<String> query = manager.createQuery(criteria);
		return query.getResultList();
	}
}