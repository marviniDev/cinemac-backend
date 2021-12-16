package com.cinemac.backend.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="sala")
public class Sala implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "seq_sala")
	@SequenceGenerator(name = "seq_sala", allocationSize = 1,sequenceName = "seq_sala")
	private Long id;
	
	private String nome;

	private Integer quant_assentos;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuant_assentos() {
		return quant_assentos;
	}

	public void setQuant_assentos(Integer quant_assentos) {
		this.quant_assentos = quant_assentos;
	}
}