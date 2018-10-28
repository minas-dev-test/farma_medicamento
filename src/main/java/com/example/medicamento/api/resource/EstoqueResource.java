package com.example.medicamento.api.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicamento.api.model.Estoque;
import com.example.medicamento.api.service.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class EstoqueResource {
	
	@Autowired
	private EstoqueService estoqueService;

	@PutMapping("/acrescentar-ao-estoque/{lote}")
	public ResponseEntity<Estoque> atualizarEstoque(@PathVariable String lote, @Valid @RequestBody Estoque estoque) {
		Estoque estoqueS = estoqueService.acrescentarAoEstoque(lote, estoque);
		return ResponseEntity.ok(estoqueS);
	}
}
