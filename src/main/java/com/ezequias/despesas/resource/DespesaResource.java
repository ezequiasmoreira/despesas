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

import com.ezequias.despesas.domain.Despesa;
import com.ezequias.despesas.dto.DespesaDTO;
import com.ezequias.despesas.service.DespesaService;

@RestController
@RequestMapping("/despesa")
public class DespesaResource {

	@Autowired
	DespesaService service;
	
	@Transactional(rollbackOn = Exception.class)
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Despesa> salvar(@RequestBody DespesaDTO dto) {
		Despesa despesa = service.converterParaDTO(dto);
		despesa = service.salvar(despesa);
		return ResponseEntity.ok().body(despesa);
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody DespesaDTO dto) {		
		service.atualizar(dto);		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		Despesa despesa = service.obterPorId(id);
		service.excluir(despesa);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Despesa>> findAll() {
		List<Despesa> despesa = service.obterTodos();  
		return ResponseEntity.ok().body(despesa);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Despesa> obterPorId(@PathVariable Integer id) {
		Despesa despesa = service.obterPorId(id);
		return ResponseEntity.ok().body(despesa);
	}
}
