package com.ezequias.despesas.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezequias.despesas.domain.Despesa;
import com.ezequias.despesas.dto.DespesaDTO;
import com.ezequias.despesas.repository.DespesaRepository;

@Service
public class DespesaService {

	@Autowired
	private DespesaRepository despesaRepository;

	public Despesa converterParaDTO(DespesaDTO dto) {		
		Despesa despesa = new Despesa();
		despesa.setDescricao(dto.getDescricao());
		despesa.setValor(dto.getValor());
		return despesa;
	}

	public Despesa salvar(Despesa despesa) {		
		despesa.setId(null);
		return despesaRepository.save(despesa);
	}

	public Despesa atualizar(DespesaDTO despesaDTO) {		
		Despesa despesa = obterPorId(despesaDTO.getId());
		despesa.setDescricao(despesaDTO.getDescricao() == null ? despesa.getDescricao() : despesaDTO.getDescricao());
		despesa.setValor(despesaDTO.getValor() == null ? despesa.getValor() : despesaDTO.getValor());
		atualizar(despesa);
		return despesa;
	}

	public Despesa atualizar(Despesa despesa) {		
		return despesaRepository.save(despesa);		
	}

	public void excluir(Despesa despesa){		
		despesaRepository.deleteById(despesa.getId());
	}

	public Despesa obterPorId(Integer despesaId) throws ObjectNotFoundException {		
		Optional<Despesa> despesa = despesaRepository.findById(despesaId);
		return despesa.orElseThrow(() -> new ObjectNotFoundException("Despesa não encontrada.", null));
	}
	
	public List<Despesa> obterTodos() throws ObjectNotFoundException {		
		return despesaRepository.findAll();
	}

}
