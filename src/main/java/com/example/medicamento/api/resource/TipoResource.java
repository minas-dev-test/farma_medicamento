package com.example.medicamento.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicamento.api.model.TipoMedicamento;
import com.example.medicamento.api.repository.TipoRepository;

@RestController
@RequestMapping("/tipos")
public class TipoResource {

	@Autowired
	private TipoRepository tipoRepository;

	@GetMapping
	public List<TipoMedicamento> listar() {
		return tipoRepository.findAll();
	}

	

}
