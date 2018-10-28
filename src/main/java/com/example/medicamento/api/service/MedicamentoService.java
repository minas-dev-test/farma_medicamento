package com.example.medicamento.api.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.medicamento.api.model.Estoque;
import com.example.medicamento.api.model.Medicamento;
import com.example.medicamento.api.repository.MedicamentoRepository;
import com.example.medicamento.api.repository.StatusRepository;

@Service
public class MedicamentoService {
	
	@Autowired 
	private MedicamentoRepository medicamentoRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Transactional
	public Medicamento salvar(Medicamento medicamento) {
		return medicamentoRepository.saveAndFlush(medicamento);
	}
	
	public Medicamento atualizar(String lote, Medicamento medicamento) {
		Medicamento medicamentoSalvo = buscarMedicamentoPeloCodigo(lote);
		BeanUtils.copyProperties(medicamento, medicamentoSalvo, "lote");
		return medicamentoRepository.save(medicamentoSalvo);
	}
	
	public Medicamento atualizarStatus(String lote, Medicamento medicamento) {
		Medicamento medicamentoSalvo = buscarMedicamentoPeloCodigo(lote);
		BeanUtils.copyProperties(medicamento, medicamentoSalvo, "lote", "estoque", "tipo");
		return medicamentoRepository.save(medicamentoSalvo);
	}
	
	public Medicamento buscarMedicamentoPeloCodigo(String lote) {
		Optional<Medicamento> medicamentoSalvo = medicamentoRepository.findById(lote);
		if (!medicamentoSalvo.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return medicamentoSalvo.get();
	}
	
	public Medicamento acrescentarAoEstoque(String lote, Estoque estoque) {
		Medicamento medicamentoSalvo = buscarMedicamentoPeloCodigo(lote);
		medicamentoSalvo.setStatus(statusRepository.findById(new Long(1)).get());
		int estoqueQtd = estoque.getQuantidade() + medicamentoSalvo.getEstoque().getQuantidade();
		medicamentoSalvo.getEstoque().setQuantidade(estoqueQtd);
		return medicamentoRepository.save(medicamentoSalvo);
	}
}
