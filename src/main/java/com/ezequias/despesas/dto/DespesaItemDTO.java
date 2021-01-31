package com.ezequias.despesas.dto;

import com.ezequias.despesas.domain.DespesaItem;

public class DespesaItemDTO {
	private Integer id;		
	private String descricao;
	private Double valor;	
	private Double quantidade;
	private Double total;
		
	public DespesaItemDTO() {}	

	public DespesaItemDTO(DespesaItem despesaItem) {
		super();
		this.id = despesaItem.getId();
		this.descricao = despesaItem.getDescricao();
		this.valor = despesaItem.getValor();
		this.quantidade = despesaItem.getQuantidade();
		this.total = despesaItem.getTotal();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	
}
