package com.cinemac.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.cinemac.backend.models.Sala;


@Repository
public interface SalaRepository extends JpaRepository<Sala, Long>{

    List<Sala> findAll();
    
    Optional<Sala> findById(Long id);
    
    Sala findByNome(String nome);

}