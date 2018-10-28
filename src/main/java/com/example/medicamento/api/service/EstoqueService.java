package com.example.medicamento.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.medicamento.api.model.Estoque;
import com.example.medicamento.api.model.Medicamento;
import com.example.medicamento.api.repository.EstoqueRepository;
import com.example.medicamento.api.repository.StatusRepository;

@Service
public class EstoqueService {
	
	@Autowired 
	private MedicamentoService medicamentoService;
	
	@Autowired 
	private EstoqueRepository estoqueRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	public Estoque acrescentarAoEstoque(String lote, Estoque estoque) {
		Medicamento medicamentoSalvo = medicamentoService.buscarMedicamentoPeloCodigo(lote);
		medicamentoSalvo.setStatus(statusRepository.findById(new Long(1)).get());
		int estoqueQtd = estoque.getQuantidade() + medicamentoSalvo.getEstoque().getQuantidade();
		medicamentoSalvo.getEstoque().setQuantidade(estoqueQtd);
		return estoqueRepository.save(medicamentoSalvo.getEstoque());
	}
	

}
