package com.ezequias.despesas.spec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ezequias.despesas.domain.Usuario;
import com.ezequias.despesas.exception.ObjectNotFoundException;
import com.ezequias.despesas.exception.ValidateException;
import com.ezequias.despesas.repository.UsuarioRepository;

@Service
public class UsuarioSpec {
	@Autowired
	private UsuarioRepository usuarioRepository;
	/*
	public Boolean validarEmailDoUsuario(Usuario usuario) {	
		Usuario usuarioEmail = usuarioRepository.findByEmail(usuario.getEmail());
		if (usuarioEmail != null) {
			Throwable throwable = new Throwable("email");
			
			throw new ValidateException(
					"E-mail: " + usuario.getEmail() + " Já está em uso.",throwable);
		}
		return true;
	}*/

}
