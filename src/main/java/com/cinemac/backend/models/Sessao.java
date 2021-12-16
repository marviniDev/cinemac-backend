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
@Table(name = "sessao")
public class Sessao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sessao")
	@SequenceGenerator(name = "seq_sessao", allocationSize = 1, sequenceName = "seq_sessao")
	private Long id;

	private String tipoAudio;

	private String horaInicio;

	private String horaFim;

	private String data;

	private Long filmeId;

	private Long salaId;

	private String animacao;

	private Float valorIngresso;

	public Float getValorIngresso() {
		return valorIngresso;
	}

	public void setValorIngresso(Float valorIngresso) {
		this.valorIngresso = valorIngresso;
	}

	public String getAnimacao() {
		return animacao;
	}

	public void setAnimacao(String animacao) {
		this.animacao = animacao;
	}

	public Long getId() {
		return id;
	}

	public void setTipoAudio(String tipoAudio) {
		this.tipoAudio = tipoAudio;
	}

	public String getTipoAudio() {
		return tipoAudio;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Long getFilmeId() {
		return filmeId;
	}

	public void setFilmeId(Long filme_id) {
		this.filmeId = filme_id;
	}

	public Long getSalaId() {
		return salaId;
	}

	public void setSalaId(Long sala_id) {
		this.salaId = sala_id;
	}
}