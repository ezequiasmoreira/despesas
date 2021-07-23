package com.ezequias.despesas.resource;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezequias.despesas.domain.Usuario;
import com.ezequias.despesas.dto.UsuarioDTO;
import com.ezequias.despesas.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	UsuarioService service;

	@CrossOrigin
	@Transactional(rollbackOn = Exception.class)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> salvar(@RequestBody UsuarioDTO dto) {
		Usuario usuario = service.converterParaDTO(dto);
		usuario = service.salvar(usuario);
		return ResponseEntity.ok().body(usuario);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UsuarioDTO dto) {
		service.atualizar(dto);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		Usuario usuario = service.obterPorId(id);
		service.excluir(usuario);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> usuario = service.obterTodos();
		return ResponseEntity.ok().body(usuario);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> obterPorId(@PathVariable Integer id) {
		Usuario usuario = service.obterPorId(id);
		return ResponseEntity.ok().body(usuario);
	}
}
