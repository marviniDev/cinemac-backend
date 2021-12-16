package com.cinemac.backend.repository;

import java.util.List;
import java.util.Optional;

import com.cinemac.backend.models.Sessao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    List<Sessao> findAll();

    Optional<Sessao> findById(Long id);

    List<Sessao> findByFilmeId(Long id);

    List<Sessao> findByFilmeIdAndSalaId(Long filmeId, Long salaId);
}