package com.example.medicamento.api.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicamento.api.builder.MultiBusinessExceptionBuilder;
import com.example.medicamento.api.exceptionhandler.MedicamentoExceptionHandler.Erro;
import com.example.medicamento.api.model.Estoque;
import com.example.medicamento.api.model.Medicamento;
import com.example.medicamento.api.repository.MedicamentoRepository;
import com.example.medicamento.api.repository.filter.MedicamentoFilter;
import com.example.medicamento.api.service.MedicamentoService;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoResource {

	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
	protected MultiBusinessExceptionBuilder mBuilder;
	
	@Autowired
	private MedicamentoService medicamentoService;

	@GetMapping
	public Page<Medicamento> pesquisar(MedicamentoFilter medicamentoFilter, Pageable pageable) {
		return medicamentoRepository.filtrar(medicamentoFilter, pageable);
	}
	
	@PostMapping
	public ResponseEntity<Object> criar(@Valid @RequestBody Medicamento medicamento, HttpServletResponse response) {
		Optional<Medicamento> medicamentoOptional = medicamentoRepository.findById(medicamento.getLote());
		
		if(medicamentoOptional.isPresent()) {
			List<Erro> erros = Arrays.asList(new Erro("Lote já cadastrado.", "Lote já cadastrado."));
			return ResponseEntity.badRequest().body(erros);
		}
		else {
			medicamento.getEstoque().setMedicamento(medicamento);
			medicamentoService.salvar(medicamento);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(medicamento);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Medicamento> buscarPeloCodigo(@PathVariable String codigo) {
		Medicamento medicamento = new Medicamento();
		medicamento.setLote(codigo);
		Example<Medicamento> exampleMed = Example.of(medicamento);
		Optional<Medicamento> medicamentoOptional = medicamentoRepository.findOne(exampleMed);
		return medicamentoOptional.isPresent() ? ResponseEntity.ok().body(medicamentoOptional.get()) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/filtrar-nomes/{nome}")
	public List<String> filtrarNomes(@PathVariable String nome) {
		return medicamentoRepository.filtrarNomes(nome);
	}
	
	@PutMapping("/{lote}")
	public ResponseEntity<Medicamento> atualizar(@PathVariable String lote, @Valid @RequestBody Medicamento medicamento) {
		Medicamento medicamentoSalvo = medicamentoService.atualizar(lote, medicamento);
		return ResponseEntity.ok(medicamentoSalvo);
	}
	
	@PutMapping("/atualizar-status/{lote}")
	public ResponseEntity<Medicamento> atualizarStatus(@PathVariable String lote, @Valid @RequestBody Medicamento medicamento) {
		Medicamento medicamentoSalvo = medicamentoService.atualizarStatus(lote, medicamento);
		return ResponseEntity.ok(medicamentoSalvo);
	}
	
	@PutMapping("/acrescentar-ao-estoque/{lote}")
	public ResponseEntity<Medicamento> atualizarEstoque(@PathVariable String lote, @Valid @RequestBody Estoque estoque) {
		Medicamento medicamentoSalvo = medicamentoService.acrescentarAoEstoque(lote, estoque);
		return ResponseEntity.ok(medicamentoSalvo);
	}
}
