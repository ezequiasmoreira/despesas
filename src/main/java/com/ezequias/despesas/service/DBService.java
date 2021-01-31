package com.ezequias.despesas.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezequias.despesas.domain.DespesaItem;
import com.ezequias.despesas.domain.Entrada;
import com.ezequias.despesas.repository.DespesaItemRepository;
import com.ezequias.despesas.repository.EntradaRepository;

@Service
public class DBService {
	
	@Autowired
	private EntradaRepository entradaRepository;
	
	@Autowired
	private DespesaItemRepository despesaItemRepository;
	
	public void instanciateTestDataBase() {
		
		Entrada entrada = new Entrada();
		entrada.setDescricao("Salario");
		entrada.setValor(3000.00);
		
		Entrada entrada2 = new Entrada();
		entrada2.setDescricao("13° Salario");
		entrada2.setValor(3000.00);
		
		entradaRepository.saveAll(Arrays.asList(entrada,entrada2));		
		
		DespesaItem despesaItem = new DespesaItem();
		despesaItem.setDescricao("Energia");
		despesaItem.setValor(90.50);
		despesaItem.setQuantidade(1.00);
		
		DespesaItem despesaItem2 = new DespesaItem();
		despesaItem2.setDescricao("Sabão");
		despesaItem2.setValor(2.90);
		despesaItem2.setQuantidade(1.00);
		
		DespesaItem despesaItem3 = new DespesaItem();
		despesaItem3.setDescricao("Refrigerante");
		despesaItem3.setValor(4.99);
		despesaItem3.setQuantidade(3.00);
		
		despesaItemRepository.saveAll(Arrays.asList(despesaItem,despesaItem2,despesaItem3));		
		
	}

}
