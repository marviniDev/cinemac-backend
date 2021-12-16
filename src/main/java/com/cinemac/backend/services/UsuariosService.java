package com.cinemac.backend.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cinemac.backend.models.Usuario;
import com.cinemac.backend.repository.UsuarioRepository;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService {

    @Autowired
    public UsuarioRepository usuariosRepository;
    
    Gson gson = new Gson();

    public String listaUsuarios() {
    	List<Usuario> teste = usuariosRepository.findAll();
		for(int i=0;i<teste.size();i++) {
			teste.get(i).setSenha("");
		}
		String json = gson.toJson(teste);
        return json;
    }

    public Optional<Usuario> listaUsuarioId(Long id) {
        Optional<Usuario> usuario = usuariosRepository.findById(id);
        usuario.get().setSenha("");
        return usuario;
    }

    public List<Usuario> listaUsuarioNome(String nome) {
        List<Usuario> usuario = usuariosRepository.findByNome(nome);
        for(int i=0;i<usuario.size();i++) {
            usuario.get(i).setSenha("");
        }
        return usuario;
    }

    public void salvaUsuario(Usuario usuarios) {
        usuariosRepository.save(usuarios);
    } 

    public void deletaUsuario(Long id) {
        usuariosRepository.deleteById(id);
    }

    public Optional<Usuario> atualizaUsuario(Long id, Usuario usuario){
        usuario.setId(id);
        usuariosRepository.save(usuario);
        return usuariosRepository.findById(id);
    }
    
    public String autenticaUsuario(Map<String, String> dados){
    	String email = dados.get("email");
    	String senha = dados.get("senha");
    	Usuario usuario = usuariosRepository.findByEmail(email);
        if(usuario!=null){
            if(usuario.getSenha().equals(senha)) {
                usuario.setSenha("");
                String result = gson.toJson(usuario);

                return (result.substring(0, result.length()-1)+","+"\"authenticate\":\"true\""+"}");
            }else {
                String teste = ("{"+"\"message\":\"Falha na autenticao\"" +","+"\"authenticate\":\"false\""+"}");
                return teste;
            }
        }else{
            String teste = ("{"+"\"message\":\"Falha na autenticao\"" +","+"\"authenticate\":\"false\""+"}");
            return teste;
        }    	
    }
   
	
}