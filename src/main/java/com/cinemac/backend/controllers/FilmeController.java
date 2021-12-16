package com.cinemac.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import com.cinemac.backend.models.Filme;
import com.cinemac.backend.repository.FilmeRepository;
import com.cinemac.backend.services.FilmeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="Cinemac Backend")
@CrossOrigin(origins="*")
public class FilmeController {
	
	@Autowired
	FilmeRepository filmeRepository;

	@Autowired
	FilmeService filmeService;

	@GetMapping("/listaFilmes")
	@ApiOperation(value = "Retorna todos os filmes")
	public ResponseEntity <List<Filme>> listaFilmes() {
		return ResponseEntity.ok().body(filmeService.listaFilmes());
	}

	@GetMapping("/listaFilmeId/{id}")
	@ApiOperation(value = "Retorna filme encontrado por id")
	public ResponseEntity <Optional<Filme>> listaFilmeId(@PathVariable (value = "id") Long id) {
		return ResponseEntity.ok().body(filmeService.findById(id));
	}

	@GetMapping("/listaFilmeNome/{nome}")
	@ApiOperation(value = "Retorna filme encontrado por nome")
	public ResponseEntity <Filme> listaFilmeId(@PathVariable (value = "nome") String nome) {
		return ResponseEntity.ok().body(filmeService.findByName(nome));
	}

	@PostMapping("/salvarFilme")
	@ApiOperation(value = "Salva filme")
	public ResponseEntity <String> salvarFilme(@RequestBody Filme Filme) {
		return ResponseEntity.ok().body(filmeService.salvaFilme(Filme));
	}

	@PostMapping("/atualizarFilme")
	@ApiOperation(value = "Atualiza filme")
	public ResponseEntity <String> atualizarFilme(@RequestBody Filme filme) {
		return ResponseEntity.ok().body(filmeService.atualizaFilme(filme));
	}

	@DeleteMapping("/deletarFilme/{id}")
	@ApiOperation(value = "Deleta filme")
	public ResponseEntity <String> deletarFilme(@PathVariable (value = "id") Long id) {
		return ResponseEntity.ok().body(filmeService.removeFilme(id));
	}
}
