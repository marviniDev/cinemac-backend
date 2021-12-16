package com.cinemac.backend.services;

import java.util.List;
import java.util.Optional;

import com.cinemac.backend.models.Sala;
import com.cinemac.backend.repository.SalaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaService {

    @Autowired
	public SalaRepository salaRepository;
    
    public Optional<Sala> listaId(Long id) {
    	return salaRepository.findById(id);
    }
    
    public Sala listaNome(String nome) {
    	return salaRepository.findByNome(nome);
    }

    public List<Sala> listarSalas() {
        return salaRepository.findAll();
    }

    public String salvaSala(Sala sala) {
        salaRepository.save(sala);
        return ("{" + "\"message\":\"Salva salva com sucesso\"" + ","+ "\"save\":\"true\"" + "}");
    }
    
    public String deletaSala(Long id) {
    	salaRepository.deleteById(id);
        return ("{" + "\"message\":\"Sala deletada com sucesso\"" + ","+ "\"deleted\":\"true\"" + "}");
    }
}
