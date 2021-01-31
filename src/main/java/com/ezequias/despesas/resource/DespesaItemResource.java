package com.ezequias.despesas.resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezequias.despesas.domain.DespesaItem;
import com.ezequias.despesas.dto.DespesaItemDTO;
import com.ezequias.despesas.service.DespesaItemService;

@RestController
@RequestMapping("/despesa-item")
public class DespesaItemResource {

	@Autowired
	DespesaItemService service;
	
	@Transactional(rollbackOn = Exception.class)
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<DespesaItem> salvar(@RequestBody DespesaItemDTO dto) {
		DespesaItem despesaItem = service.converterParaDTO(dto);
		despesaItem = service.salvar(despesaItem);
		return ResponseEntity.ok().body(despesaItem);
	}
	
	@Transactional(rollbackOn = Exception.class)
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<List<DespesaItem>> salvar(@RequestBody List<DespesaItemDTO> listDto) {
		
		List<DespesaItem> itens = new ArrayList<DespesaItem>();
		for (DespesaItemDTO despesaItemDTO : listDto) {
			DespesaItem despesaItem = service.converterParaDTO(despesaItemDTO);
			despesaItem = service.salvar(despesaItem);	
			itens.add(despesaItem);
		}
		
		return ResponseEntity.ok().body(itens);
	}
	
	@Transactional(rollbackOn = Exception.class)
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody DespesaItemDTO dto) {		
		service.atualizar(dto);		
		return ResponseEntity.noContent().build();
	}
	
	@Transactional(rollbackOn = Exception.class)
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		DespesaItem despesaItem = service.obterPorId(id);
		service.excluir(despesaItem);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<DespesaItem>> findAll() {
		List<DespesaItem> despesaItem = service.obterTodos();  
		return ResponseEntity.ok().body(despesaItem);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<DespesaItem> obterPorId(@PathVariable Integer id) {
		DespesaItem despesaItem = service.obterPorId(id);
		return ResponseEntity.ok().body(despesaItem);
	}
}
