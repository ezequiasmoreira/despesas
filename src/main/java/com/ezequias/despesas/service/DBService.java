package com.ezequias.despesas.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezequias.despesas.domain.Despesa;
import com.ezequias.despesas.domain.Entrada;
import com.ezequias.despesas.repository.DespesaRepository;
import com.ezequias.despesas.repository.EntradaRepository;

@Service
public class DBService {
	
	@Autowired
	private EntradaRepository entradaRepository;
	@Autowired
	private DespesaRepository despesaRepository;
	
	public void instanciateTestDataBase() {
		
		Entrada entrada = new Entrada();
		entrada.setDescricao("Salario");
		entrada.setValor(3000.00);
		
		Entrada entrada2 = new Entrada();
		entrada2.setDescricao("13° Salario");
		entrada2.setValor(3000.00);
		
		entradaRepository.saveAll(Arrays.asList(entrada, entrada2));	
		
		Despesa despesa1 = new Despesa();
		despesa1.setDescricao("Energia");
		despesa1.setValor(100.00);
		
		Despesa despesa2 = new Despesa();
		despesa2.setDescricao("Aguá");
		despesa2.setValor(50.00);
		
		despesaRepository.saveAll(Arrays.asList(despesa1, despesa2));	

	}
}
