package com.ezequias.despesas.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezequias.despesas.domain.Entrada;
import com.ezequias.despesas.repository.EntradaRepository;

@Service
public class DBService {
	
	@Autowired
	private EntradaRepository entradaRepository;
	
	public void instanciateTestDataBase() {
		
		Entrada entrada = new Entrada();
		entrada.setDescricao("Salario");
		entrada.setValor(3000.00);
		
		Entrada entrada2 = new Entrada();
		entrada2.setDescricao("13° Salario");
		entrada2.setValor(3000.00);
		
		entradaRepository.saveAll(Arrays.asList(entrada,entrada2));		
		
	}

}
