package com.example.medicamento.api.repository.medicamento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.medicamento.api.model.Medicamento;
import com.example.medicamento.api.repository.filter.MedicamentoFilter;

public interface MedicamentoRepositoryQuery {

	public Page<Medicamento> filtrar(MedicamentoFilter medicamentoFilter, Pageable pageable);
	
	List<String> filtrarNomes(String nome);
	
}