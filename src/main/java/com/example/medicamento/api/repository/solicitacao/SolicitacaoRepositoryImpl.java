package com.example.medicamento.api.repository.solicitacao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import com.example.medicamento.api.model.Medicamento_;
import com.example.medicamento.api.model.Solicitacao;
import com.example.medicamento.api.model.Solicitacao_;

public class SolicitacaoRepositoryImpl implements SolicitacaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Solicitacao filtrarSolicitacaoAtual(String lote) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Solicitacao> criteria = builder.createQuery(Solicitacao.class);
		Root<Solicitacao> root = criteria.from(Solicitacao.class);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(lote)) {
			predicates.add(builder.equal(root.get(Solicitacao_.confirmada), false));
			predicates.add(builder.equal(root.get(Solicitacao_.excluida), false));
			predicates.add(builder.equal(root.get(Solicitacao_.medicamento).get(Medicamento_.lote), lote));
		}
		
		criteria.where(predicates.toArray(new Predicate[predicates.size()]));
		TypedQuery<Solicitacao> query = manager.createQuery(criteria);
		return query.getSingleResult();
	}
}