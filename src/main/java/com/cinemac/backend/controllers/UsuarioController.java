package com.cinemac.backend.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cinemac.backend.models.Usuario;
import com.cinemac.backend.repository.UsuarioRepository;
import com.cinemac.backend.services.UsuariosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value="/api")
@Api(value="Cinemac Backend")
@CrossOrigin(origins="*")
public class UsuarioController {
	
	@Autowired
	UsuarioRepository usuariosRepository;

    @Autowired
	UsuariosService usuariosService;

	@GetMapping("/usuarios")
	@ApiOperation(value = "Retorna uma lista de usuarios")
	public ResponseEntity<String>  listaUsuarios(){
		return ResponseEntity.ok().body(usuariosService.listaUsuarios());
  	}

	@GetMapping("/usuarioId/{id}")
	@ApiOperation(value = "Retorna um usuario encontrado pelo id")
	public ResponseEntity<Optional<Usuario>> listaUsuarioId(@PathVariable Long id){
		return ResponseEntity.ok().body(usuariosService.listaUsuarioId(id));
	}

	@GetMapping("/usuarioNome/{nome}")
	@ApiOperation(value = "Retorna um usuario encontrado pelo nome")
	public ResponseEntity <List<Usuario>> ListaUsuarioNome(@PathVariable String nome){
		return ResponseEntity.ok(usuariosService.listaUsuarioNome(nome));
	}

	@PostMapping("/usuario")
	@ApiOperation(value = "Salva um usuario")
	public ResponseEntity<Void> salvaUsuario(@RequestBody Usuario Usuarios) {
        usuariosService.salvaUsuario(Usuarios);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/autenticaUsuario")
	@ApiOperation(value = "Autentica Usuario")
	public ResponseEntity<String> authUsuario(@RequestBody Map<String, String> dados) {
		return ResponseEntity.ok().body(usuariosService.autenticaUsuario(dados));
	}

	@DeleteMapping("/deletaUsuario/{id}")
	@ApiOperation(value = "Deleta um Usuarios")
	public ResponseEntity<Void> deletaUsuario(@PathVariable(value="id") Long id) {
		usuariosService.deletaUsuario(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/atualizaUsuario/{id}")
	@ApiOperation(value = "Atualiza um usuario pelo id")
	public ResponseEntity<Usuario> atualizaUsuario(@PathVariable(value="id") Long id, @RequestBody Usuario usuarios) {
		usuariosService.atualizaUsuario(id, usuarios);
		return ResponseEntity.noContent().build();
	}
}