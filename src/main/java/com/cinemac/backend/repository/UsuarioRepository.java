package com.cinemac.backend.repository;

import java.util.List;
import java.util.Optional;

import com.cinemac.backend.models.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    List<Usuario> findAll();

	Optional<Usuario> findById(Long id);
	
	Usuario findByEmail(String email);

	List<Usuario> findByNome(String nome);
	
}