package com.cinemac.backend.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.cinemac.backend.models.Filme;
import com.cinemac.backend.models.Sessao;
import com.cinemac.backend.repository.FilmeRepository;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {

	@Autowired
	public FilmeRepository filmeRepository;

	@Autowired
	public SessaoService sessaoService;

	Gson gson = new Gson();
	Date data = new Date();

	public List<Filme> listaFilmes() {
		List<Filme> filmes = filmeRepository.findAll();
		for (int i = 0; i < filmes.size(); i++) {
			String titulo = filmes.get(i).getTitulo();
			File file = new File(System.getProperty("user.dir") + "/img/filmes/" + titulo + ".png");
			if (file.exists() && !file.isDirectory()) {
				String base64 = this.encodeFileToBase64(file);
				filmes.get(i).setImagem(base64);
			}

		}
		return filmes;
	}

	public Optional<Filme> findById(Long id) {
		Optional<Filme> filme = filmeRepository.findById(id);
		filme.get().setImagem(encodeFileToBase64(
				new File(System.getProperty("user.dir") + "/img/filmes/" + filme.get().getTitulo() + ".png")));
		return filme;
	}

	public Filme findByName(String name) {
		Filme filme = filmeRepository.findByTitulo(name);
		filme.setImagem(encodeFileToBase64(
				new File(System.getProperty("user.dir") + "/img/filmes/" + filme.getTitulo() + ".png")));
		return filme;
	}

	public String salvaFilme(Filme filme) {
		String titulo = filme.getTitulo();
		if (filmeRepository.findByTitulo(titulo) == null) {
			String imagem = filme.getImagem();
			if (imagem != null) {
				String base64Image = imagem.split(",")[1];
				byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
				Path destinationFile = Paths
						.get(System.getProperty("user.dir") + "/img/filmes/" + filme.getTitulo() + ".png");

				try {
					Files.write(destinationFile, imageBytes);
					filme.setImagem("");
					filmeRepository.save(filme);
					String filmeJson = gson.toJson(filme);
					return (filmeJson.substring(0, filmeJson.length() - 1) + "," + "\"save\":\"true\"" + "}");
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				String filmeJson = ("{" + "\"message\":\"Imagem é obrigatória\"" + "," + "\"save\":\"false\"" + "}");
				return filmeJson;
			}

		} else {
			String filmeJson = ("{" + "\"message\":\"Filme já cadastrado\"" + "," + "\"save\":\"false\"" + "}");
			return filmeJson;
		}
		String filmeJson = ("{" + "\"message\":\"Falha ao salvar o filme\"" + "," + "\"save\":\"false\"" + "}");
		return filmeJson;
	}

	public String removeFilme(Long id) {
		Filme filme = filmeRepository.findById(id).get();
		String titulo = filme.getTitulo();
		List<Sessao> sessoes = sessaoService.buscaSessaoFilme(titulo);
		if (sessoes.size() == 0) {
			File file = new File(System.getProperty("user.dir") + "/img/filmes/"
					+ titulo + ".png");
			file.delete();
			filmeRepository.deleteById(id);
			return ("{" + "\"message\":\"Filme removido com sucesso\"" + "," + "\"save\":\"true\"" + "}");
		} else {
			return ("{" + "\"message\":\"O filme já está vinculado a uma sessão\"" + "," + "\"save\":\"false\"" + "}");
		}
	}

	public String encodeFileToBase64(File file) {
		try {
			byte[] fileContent = Files.readAllBytes(file.toPath());
			return ("data:image/png;base64," + Base64.getEncoder().encodeToString(fileContent));
		} catch (IOException e) {
			throw new IllegalStateException("could not read file " + file, e);
		}
	}

	public String atualizaFilme(Filme filme) {
		File file = new File(System.getProperty("user.dir") + "/img/filmes/" + filme.getTitulo() + ".png");
		if (file.exists() && !file.isDirectory()) {
			file.delete();
		}

		String imagem = filme.getImagem();
		String base64Image = imagem.split(",")[1];
		byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);

		Path destinationFile = Paths.get(System.getProperty("user.dir") + "/img/filmes/",
				filme.getTitulo() + ".png");
		try {
			Files.write(destinationFile, imageBytes);
			filme.setImagem("");
		} catch (Exception e) {
			e.printStackTrace();
		}

		filme.setImagem("");
		filmeRepository.save(filme);
		return "{\"message\":\"Filme atualizado com sucesso\",\"save\":\"true\"}";
	}
}
