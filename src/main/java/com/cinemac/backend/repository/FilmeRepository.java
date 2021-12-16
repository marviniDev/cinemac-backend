package com.cinemac.backend.repository;

import java.util.List;
import java.util.Optional;

import com.cinemac.backend.models.Filme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{

    List<Filme> findAll();

	Filme findByTitulo(String titulo);

    Optional<Filme> findById(Long id);
	
}