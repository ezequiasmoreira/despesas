package com.ezequias.despesas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezequias.despesas.domain.Usuario;
import com.ezequias.despesas.dto.UsuarioDTO;
import com.ezequias.despesas.exception.ObjectNotFoundException;
import com.ezequias.despesas.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario findById(Integer id){
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Usuario salvar(Usuario usuario) {
		usuario.setId(null);
		return usuarioRepository.save(usuario);
	}
	
	public Usuario fromDTO(UsuarioDTO usuarioDTO) {		
		return new Usuario(usuarioDTO);
	}
	
	public Usuario update(Usuario usuario) {
        Optional<Usuario> updateUsuario = usuarioRepository.findById(usuario.getId());
        return updateUsuario.map(u -> usuarioRepository.save(new Usuario(u.getId(), usuario.getNome(), usuario.getSobrenome(), usuario.getEmail(),
        		u.getSenha(), u.isAtivo())))
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));
    }

    public void delete(Integer id) {
    	usuarioRepository.deleteById(id);
    }

}
