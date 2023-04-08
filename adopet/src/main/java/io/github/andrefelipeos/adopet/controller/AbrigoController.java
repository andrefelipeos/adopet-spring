package io.github.andrefelipeos.adopet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.andrefelipeos.adopet.domain.abrigo.Abrigo;
import io.github.andrefelipeos.adopet.domain.abrigo.AbrigoRepository;
import io.github.andrefelipeos.adopet.domain.abrigo.DadosCadastroAbrigo;
import io.github.andrefelipeos.adopet.domain.abrigo.DadosListagemAbrigos;
import jakarta.validation.Valid;

@RestController
@RequestMapping("abrigos")
public class AbrigoController {

	@Autowired
	private AbrigoRepository abrigoRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<Abrigo> cadastrar(@RequestBody @Valid DadosCadastroAbrigo dados) {
		Abrigo abrigo = abrigoRepository.save(new Abrigo(dados));
		return ResponseEntity.ok(abrigo);
	}

	@GetMapping
	public ResponseEntity<List<DadosListagemAbrigos>> listar() {
		List<Abrigo> abrigos = abrigoRepository.findAll();
		return ResponseEntity.ok(abrigos.stream().map(DadosListagemAbrigos::new).toList());
	}

}
