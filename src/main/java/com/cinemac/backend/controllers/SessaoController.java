package com.cinemac.backend.controllers;

import java.util.List;

import com.cinemac.backend.models.Sessao;
import com.cinemac.backend.repository.SessaoRepository;
import com.cinemac.backend.services.SessaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "Cinemac Backend")
@CrossOrigin(origins = "*")
public class SessaoController {

    @Autowired
    SessaoRepository sessaoRepository;

    @Autowired
    SessaoService sessaoService;

    @GetMapping("/sessoes")
    @ApiOperation(value = "Retorna lista de todas as sessoes")
    public ResponseEntity<List<Sessao>> getSessoes() {
        return ResponseEntity.ok().body(sessaoRepository.findAll());
    }

    @PostMapping("/salvaSessao")
    @ApiOperation(value = "Salva uma sessao")
    public ResponseEntity<String> salvaSessao(@RequestBody Sessao sessao) {
        return ResponseEntity.ok().body(sessaoService.salvaSessao(sessao));
    }

    @PostMapping("/editaSessao")
    @ApiOperation(value = "Salva uma sessao")
    public ResponseEntity<Void> editaSessao(@RequestBody Sessao sessao) {
        sessaoRepository.save(sessao);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deletaSessao/{id}")
    @ApiOperation(value = "Deleta uma sessao")
    public ResponseEntity<String> deletaSessao(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(sessaoService.deletaSessao(id));
    }

    @GetMapping("/buscaSessaoFilme/{nomeFilme}")
    @ApiOperation(value = "Busca sessao por nome do filme")
    public ResponseEntity<List<Sessao>> buscaSessaoFilme(@PathVariable(value = "nomeFilme") String nomeFilme) {
        return ResponseEntity.ok().body(sessaoService.buscaSessaoFilme(nomeFilme));
    }
}
