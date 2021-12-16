package com.cinemac.backend.controllers;

import java.util.List;
import java.util.Optional;

import com.cinemac.backend.models.Sala;
import com.cinemac.backend.repository.SalaRepository;
import com.cinemac.backend.services.SalaService;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="Cinemac Backend")
@CrossOrigin(origins="*")
public class SalaController {

    @Autowired
    SalaRepository salaRepository;

    @Autowired
    SalaService salaService;

    @GetMapping("/salas")
    @ApiOperation(value="Retorna todas as salas")
    public ResponseEntity<List<Sala>> getAllSalas() {
        return ResponseEntity.ok(salaService.listarSalas());
    }
    
    @GetMapping("/salaId/{id}")
    @ApiOperation(value="Retorna sala por id")
    public ResponseEntity<Optional<Sala>> getSalaId(@PathVariable(value="id") Long id){
    	return ResponseEntity.ok().body(salaService.listaId(id));
    }
    
    @GetMapping("/salaNome/{nome}")
    @ApiOperation(value="Retorna sala por nome")
    public ResponseEntity<Sala> getSalaNome(@PathVariable(value="nome") String nome){
    	return ResponseEntity.ok().body(salaService.listaNome(nome));
    }

    @PostMapping("/salvarSala")
	@ApiOperation(value = "Salva uma sala")
    public ResponseEntity<String> salvaSala(@RequestBody Sala sala) {
        return ResponseEntity.ok().body(salaService.salvaSala(sala));
    }

    @PostMapping("/atualizaSala")
    @ApiOperation(value = "Atualiza uma sala")
    public ResponseEntity<Void> atualizaSala(@RequestBody Sala sala) {
        salaService.salvaSala(sala);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/deletaSala/{id}")
    @ApiOperation(value="Deleta sala pelo id")
    public ResponseEntity<String> deletaSala(@PathVariable(value="id") Long id){
    	return ResponseEntity.ok().body(salaService.deletaSala(id));
    }

}
