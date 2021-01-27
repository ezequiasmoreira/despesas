package com.ezequias.despesas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezequias.despesas.domain.Entrada;
import com.ezequias.despesas.dto.EntradaDTO;
import com.ezequias.despesas.repository.EntradaRepository;

@Service
public class EntradaService {
	
	@Autowired
	private EntradaRepository entradaRepository;
	
	public Entrada converterParaDTO(EntradaDTO dto) {
		Entrada  entrada = new Entrada();		
		entrada.setDescricao(dto.getDescricao());
		entrada.setValor(dto.getValor());
		return entrada;		
	}
	
	public Entrada salvar(Entrada entrada) {
		entrada.setId(null);
		return entradaRepository.save(entrada);
	}

}
