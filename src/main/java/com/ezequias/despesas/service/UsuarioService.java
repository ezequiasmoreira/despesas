package com.ezequias.despesas.service;

import java.util.List;
import java.util.Optional;

import com.ezequias.despesas.domain.Usuario;
import com.ezequias.despesas.dto.UsuarioDTO;
import com.ezequias.despesas.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezequias.despesas.repository.UsuarioRepository;
import com.ezequias.despesas.spec.UsuarioSpec;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioSpec usuarioSpec;

	public Usuario converterParaDTO(UsuarioDTO dto) {		
		Usuario usuario = new Usuario();
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(dto.getSenha());
		return usuario;
	}

	public Usuario salvar(Usuario usuario) {
		usuarioSpec.validarEmailDoUsuario(usuario);
		usuario.setId(null);
		return usuarioRepository.save(usuario);
	}

	public Usuario atualizar(UsuarioDTO usuarioDTO) {		
		Usuario usuario = obterPorId(usuarioDTO.getId());
		usuario.setNome(usuarioDTO.getNome() == null ? usuario.getNome() : usuarioDTO.getNome());
		usuario.setSenha(usuarioDTO.getSenha() == null ? usuario.getSenha() : usuarioDTO.getSenha());
		atualizar(usuario);
		return usuario;
	}

	public Usuario atualizar(Usuario usuario) {		
		return usuarioRepository.save(usuario);		
	}

	public void excluir(Usuario usuario){		
		usuarioRepository.deleteById(usuario.getId());
	}

	public Usuario obterPorId(Integer usuarioId) throws ObjectNotFoundException {		
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado."));
	}
	
	public List<Usuario> obterTodos() throws ObjectNotFoundException {		
		return usuarioRepository.findAll();
	}

}
