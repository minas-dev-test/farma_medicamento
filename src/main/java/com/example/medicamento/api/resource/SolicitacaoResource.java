package com.example.medicamento.api.resource;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medicamento.api.exceptionhandler.MedicamentoExceptionHandler.Erro;
import com.example.medicamento.api.model.Solicitacao;
import com.example.medicamento.api.repository.MedicamentoRepository;
import com.example.medicamento.api.repository.SolicitacaoRepository;
import com.example.medicamento.api.repository.StatusRepository;
import com.example.medicamento.api.util.DateUtils;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoResource {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private MedicamentoRepository medicamentoRepository;

	@PostMapping
	public ResponseEntity<Object> criar(@Valid @RequestBody Solicitacao solicitacao, HttpServletResponse response) {

		if(solicitacao.getMedicamento().getEstoque().getQuantidade() < solicitacao.getQuantidade()) {
			List<Erro> erros = Arrays.asList(new Erro("Estoque insuficiente.", "Estoque insuficente."));
			return ResponseEntity.badRequest().body(erros);
		}
		else {
			solicitacao.setDataFinal(DateUtils.adicionarDiasUteis(new Date(), 3));
			solicitacao.setConfirmada(false);
			solicitacao.setExcluida(false);
			solicitacao.setMedicamento(medicamentoRepository.findById(solicitacao.getMedicamento().getLote()).get());
			solicitacao.getMedicamento().setStatus(statusRepository.findById(new Long(2)).get());
			solicitacaoRepository.save(solicitacao);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(solicitacao);
	}
	
	@PutMapping("/excluir-solicitacao/{lote}")
	public ResponseEntity<Solicitacao> excluirSolicitacao(@PathVariable String lote, @Valid @RequestBody Solicitacao solicitacao) {
		solicitacao.setMedicamento(medicamentoRepository.findById(solicitacao.getMedicamento().getLote()).get());
		solicitacao.getMedicamento().setStatus(statusRepository.findById(new Long(1)).get());
		solicitacao.setExcluida(true);
		solicitacao = solicitacaoRepository.save(solicitacao);
		return ResponseEntity.ok(solicitacao);
	}
	
	@PutMapping("/confirmar-solicitacao/{lote}")
	public ResponseEntity<Solicitacao> confirmarSolicitacao(@PathVariable String lote, @Valid @RequestBody Solicitacao solicitacao) {
		solicitacao.setConfirmada(true);
		solicitacao.setMedicamento(medicamentoRepository.findById(solicitacao.getMedicamento().getLote()).get());
		solicitacao.getMedicamento().getEstoque().setQuantidade(solicitacao.getMedicamento().getEstoque().getQuantidade() - solicitacao.getQuantidade());
		if(solicitacao.getMedicamento().getEstoque().getQuantidade() == 0) {
			solicitacao.getMedicamento().setStatus(statusRepository.findById(new Long(3)).get());
		}
		else {
			solicitacao.getMedicamento().setStatus(statusRepository.findById(new Long(1)).get());
		}
		solicitacao = solicitacaoRepository.save(solicitacao);
		return ResponseEntity.ok(solicitacao);
	}
}
