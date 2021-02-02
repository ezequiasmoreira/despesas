package com.ezequias.despesas.service;

import java.util.List;
import java.util.Optional;

import com.ezequias.despesas.exception.ObjectNotFoundException;
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
		Entrada entrada = new Entrada();
		entrada.setDescricao(dto.getDescricao());
		entrada.setValor(dto.getValor());
		return entrada;
	}

	public Entrada salvar(Entrada entrada) {		
		entrada.setId(null);
		return entradaRepository.save(entrada);
	}

	public Entrada atualizar(EntradaDTO entradaDTO) {		
		Entrada entrada = obterPorId(entradaDTO.getId());
		entrada.setDescricao(entradaDTO.getDescricao() == null ? entrada.getDescricao() : entradaDTO.getDescricao());
		entrada.setValor(entradaDTO.getValor() == null ? entrada.getValor() : entradaDTO.getValor());
		atualizar(entrada);
		return entrada;
	}

	public Entrada atualizar(Entrada entrada) {		
		return entradaRepository.save(entrada);		
	}

	public void excluir(Entrada entrada){		
		entradaRepository.deleteById(entrada.getId());
	}

	public Entrada obterPorId(Integer entradaId) throws ObjectNotFoundException {		
		Optional<Entrada> entrada = entradaRepository.findById(entradaId);
		return entrada.orElseThrow(() -> new ObjectNotFoundException("Entrada não encontrado."));
	}
	
	public List<Entrada> obterTodos() throws ObjectNotFoundException {		
		return entradaRepository.findAll();
	}

}
