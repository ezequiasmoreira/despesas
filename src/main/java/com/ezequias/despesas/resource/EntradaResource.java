package com.ezequias.despesas.resource;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezequias.despesas.domain.Entrada;
import com.ezequias.despesas.dto.EntradaDTO;
import com.ezequias.despesas.service.EntradaService;

@RestController
@RequestMapping("/entrada")
public class EntradaResource {

	@Autowired
	EntradaService service;
	
	@Transactional(rollbackOn = Exception.class)
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Entrada> salvar(@RequestBody EntradaDTO dto) {
		Entrada entrada = service.converterParaDTO(dto);
		entrada = service.salvar(entrada);
		return ResponseEntity.ok().body(entrada);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody EntradaDTO dto) {		
		service.atualizar(dto);		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		Entrada entrada = service.obterPorId(id);
		service.excluir(entrada);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Entrada>> findAll() {
		List<Entrada> entrada = service.obterTodos();  
		return ResponseEntity.ok().body(entrada);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Entrada> obterPorId(@PathVariable Integer id) {
		Entrada entrada = service.obterPorId(id);
		return ResponseEntity.ok().body(entrada);
	}
}
