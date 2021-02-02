package com.ezequias.despesas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ezequias.despesas.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezequias.despesas.domain.DespesaItem;
import com.ezequias.despesas.dto.DespesaItemDTO;
import com.ezequias.despesas.repository.DespesaItemRepository;

@Service
public class DespesaItemService {

	@Autowired
	private DespesaItemRepository despesaItemRepository;

	public DespesaItem converterParaDTO(DespesaItemDTO dto) {		
		DespesaItem despesaItem = new DespesaItem();
		despesaItem.setDescricao(dto.getDescricao());
		despesaItem.setValor(dto.getValor());
		despesaItem.setQuantidade(dto.getQuantidade());
		return despesaItem;
	}

	public DespesaItem salvar(DespesaItem despesaItem) {		
		despesaItem.setId(null);
		return despesaItemRepository.save(despesaItem);
	}
	

	public List<DespesaItem> salvarTodos(List<DespesaItemDTO> listDespesaItensDTO) {		
		List<DespesaItem> itens = new ArrayList<DespesaItem>();
		for (DespesaItemDTO despesaItemDTO : listDespesaItensDTO) {
			DespesaItem despesaItem = converterParaDTO(despesaItemDTO);
			despesaItem = salvar(despesaItem);				
			itens.add(despesaItem);
		}
		return itens;
	}

	public DespesaItem atualizar(DespesaItemDTO despesaItemDTO) {		
		DespesaItem despesaItem = obterPorId(despesaItemDTO.getId());
		despesaItem.setDescricao(despesaItemDTO.getDescricao() == null ? despesaItem.getDescricao() : despesaItemDTO.getDescricao());
		despesaItem.setValor(despesaItemDTO.getValor() == null ? despesaItem.getValor() : despesaItemDTO.getValor());
		despesaItem.setQuantidade(despesaItemDTO.getQuantidade() == null ? despesaItem.getQuantidade() : despesaItemDTO.getQuantidade());
		atualizar(despesaItem);
		return despesaItem;
	}

	public DespesaItem atualizar(DespesaItem despesaItem) {		
		return despesaItemRepository.save(despesaItem);		
	}

	public void excluir(DespesaItem despesaItem){		
		despesaItemRepository.deleteById(despesaItem.getId());
	}

	public DespesaItem obterPorId(Integer despesaItemId) throws ObjectNotFoundException {		
		Optional<DespesaItem> despesaItem = despesaItemRepository.findById(despesaItemId);
		return despesaItem.orElseThrow(() -> new ObjectNotFoundException("DespesaItem não encontrado."));
	}
	
	public List<DespesaItem> obterTodos() throws ObjectNotFoundException {		
		return despesaItemRepository.findAll();
	}

}
