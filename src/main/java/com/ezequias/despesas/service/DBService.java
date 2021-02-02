package com.ezequias.despesas.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezequias.despesas.domain.Despesa;
import com.ezequias.despesas.domain.DespesaItem;
import com.ezequias.despesas.domain.Entrada;
import com.ezequias.despesas.repository.DespesaRepository;
import com.ezequias.despesas.repository.DespesaItemRepository;
import com.ezequias.despesas.repository.EntradaRepository;

@Service
public class DBService {
	
	@Autowired
	private EntradaRepository entradaRepository;
	@Autowired
	private DespesaRepository despesaRepository;
	
	@Autowired
	private DespesaItemRepository despesaItemRepository;
	
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
		despesa2.setDescricao("Compra");
		despesa2.setValor(50.00);
		
		despesaRepository.saveAll(Arrays.asList(despesa1, despesa2));	
		
		DespesaItem despesaItem = new DespesaItem();
		despesaItem.setDescricao("Energia");
		despesaItem.setValor(90.50);
		despesaItem.setQuantidade(1.00);
		despesaItem.setDespesa(despesa1);		
		
		DespesaItem despesaItem2 = new DespesaItem();
		despesaItem2.setDescricao("Sabão");
		despesaItem2.setValor(2.90);
		despesaItem2.setQuantidade(1.00);
		despesaItem2.setDespesa(despesa2);
		
		DespesaItem despesaItem3 = new DespesaItem();
		despesaItem3.setDescricao("Refrigerante");
		despesaItem3.setValor(4.99);
		despesaItem3.setQuantidade(3.00);
		despesaItem3.setDespesa(despesa2);
		
		despesaItemRepository.saveAll(Arrays.asList(despesaItem,despesaItem2,despesaItem3));	
		
		//vincular os itens na despesa
		despesa1.getItens().add(despesaItem);
		despesa2.getItens().add(despesaItem2);
		despesa2.getItens().add(despesaItem3);
		despesaRepository.saveAll(Arrays.asList(despesa1, despesa2));	
	}
}
